package  main.modes.extractdata.importexportfiles;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {

	private URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
	private String initalFilePath;

	public FileChooser() {
		try {
			initalFilePath = URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	public String selectPath() {
		JFileChooser jfc = new JFileChooser(initalFilePath);
		int returnValue = jfc.showOpenDialog(null);
		File selectedFile = FileSystemView.getFileSystemView().getHomeDirectory();

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		return selectedFile.getAbsolutePath();
	}

	public String selectFolder() {
		JFileChooser jfc = new JFileChooser(initalFilePath);
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setAcceptAllFileFilterUsed(false);
		int returnValue = jfc.showOpenDialog(null);
		File selectedFile = FileSystemView.getFileSystemView().getHomeDirectory();

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		return selectedFile.getAbsolutePath();
	}

	public File getFile(String url) {
		File selectedFile = getFile(url);
		return selectedFile;
	}

}
