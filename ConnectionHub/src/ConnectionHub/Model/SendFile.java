package ConnectionHub.Model;

import java.io.File;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
 
public class SendFile {
 
 public static boolean startFTP(FTPFile ftpFile){
 
  StandardFileSystemManager manager = new StandardFileSystemManager();
 
  try {
 
   String serverAddress = ftpFile.getHost();
   String userId = ftpFile.getUser();
   String password = ftpFile.getPassword();
   String remoteDirectory = ftpFile.getDestinationPath();
   String localDirectory = ftpFile.getSourcePath();
 
   MaskFilter filter = new MaskFilter();
   File[] filesFound;
	
   filter.Mask = ftpFile.getMask();
   File fileSearch = new File(localDirectory);
   filesFound = fileSearch.listFiles(filter);
   
   //Initializes the file manager
   manager.init();
  
   //Setup our SFTP configuration
   FileSystemOptions opts = new FileSystemOptions();
   SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
   SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
   SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

   for (File file : filesFound) {
     // Check if file exists
	 if (!file.exists())
      throw new RuntimeException("Error. Local file not found");
 
     //Create the SFTP URI using the host name, userid, password,  remote path and file name
     String sftpUri = "sftp://" + userId + ":" + password +  "@" + serverAddress + "/" + remoteDirectory + file.getName();
    
     // Create local file object
     FileObject localFile = manager.resolveFile(file.getAbsolutePath());
 
     // Create remote file object
     FileObject remoteFile = manager.resolveFile(sftpUri, opts);
 
     FileSystemManager fsManager = VFS.getManager();
     FileObject listendir = fsManager.resolveFile("/home/username/monitored/"); 
     
     DefaultFileMonitor fm = new DefaultFileMonitor(new FileListener() { 
    	 @Override 
    	 public void fileDeleted(FileChangeEvent event) throws Exception 
    	 { System.out.println(event.getFile().getName().getPath()+" Deleted."); } 
    	 @Override 
    	 public void fileCreated(FileChangeEvent event) throws Exception 
    	 { System.out.println(event.getFile().getName().getPath()+" Created."); } 
    	 @Override 
    	 public void fileChanged(FileChangeEvent event) throws Exception 
    	 { System.out.println(event.getFile().getName().getPath()+" Changed."); } });
     
     fm.setRecursive(true);
     fm.addFile(listendir);
     fm.start(); 
     
     // Copy local file to sftp server
     remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
     System.out.println("File upload successful");
   }
   
  }
  catch (Exception ex) {
   ex.printStackTrace();
   return false;
  }
  finally {
   manager.close();
  }
 
  return true;
 }
 
  
}
