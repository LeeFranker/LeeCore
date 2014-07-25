package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

/**
 * 关于文件常用的操作
 * 
 * @author LeeFranker
 * 
 */
public class FileUtils {
	private static final int IO_BUFFER_SIZE = 8 * 1024; // 8k
	private static final int BUFFER_SIZE = 2 * 1024;// 2K

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileDir, String fileName)
			throws IOException {
		File file = new File(fileDir, fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param fileDir
	 * @return
	 */
	public static File createDir(String fileDir) {
		File file = new File(fileDir);
		file.mkdir();
		return file;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileDir, String fileName) {
		File file = new File(fileDir, fileName);
		return file.exists();
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists())
			return file.delete();
		else
			return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileDir, String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists())
			return file.delete();
		else
			return false;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param fileDir
	 * @return
	 */
	public static boolean deleteDirectory(String fileDir) {
		if (TextUtils.isEmpty(fileDir))
			return false;
		if (!fileDir.endsWith(File.separator))
			fileDir += File.separator;
		File dirFile = new File(fileDir);
		if (!dirFile.isDirectory() || !dirFile.exists())
			return false;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile())
				deleteFile(files[i].getAbsolutePath());
			else
				deleteDirectory(files[i].getAbsolutePath());
		}
		if (dirFile.delete())
			return true;
		else
			return false;
	}

	/**
	 * 拷贝文件
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			File oldFile = new File(oldPath);
			if (oldFile.exists()) {
				FileInputStream input = new FileInputStream(oldFile);
				FileOutputStream out = new FileOutputStream(newPath);
				int byteRead = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((byteRead = input.read(buffer)) != -1) {
					out.write(buffer, 0, byteRead);
				}
				out.flush();
				out.close();
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyDir(String oldPath, String newPath) {
		try {
			File file = new File(newPath);
			if (!file.exists())
				file.mkdir();
			String[] names = file.list();
			File temp = null;
			for (int i = 0; i < names.length; i++) {
				if (!oldPath.endsWith(File.separator))
					temp = new File(oldPath + File.separator + names[i]);
				else
					temp = new File(oldPath + names[i]);
				if (temp.isFile()) {
					FileInputStream fis = new FileInputStream(temp);
					FileOutputStream out = new FileOutputStream(newPath
							+ File.separator + temp.getName().toString());
					byte[] buffer = new byte[BUFFER_SIZE];
					int len = 0;
					while ((len = fis.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.flush();
					out.close();
					fis.close();
				}
				if (temp.isDirectory())
					copyDir(oldPath + File.separator + names[i], newPath
							+ File.separator + names[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字符串写入文件
	 * 
	 * @param string
	 * @param fileName
	 */
	public static void wirteFileString(String string, String fileName) {
		try {
			deleteFile(fileName);
			File file = createFile(fileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(string);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字符串写入文件
	 * 
	 * @param file
	 * @param content
	 */
	public static void writeFileString(File file, String content) {
		FileOutputStream os = null;
		try {
			byte[] buffer = content.getBytes("UTF-8");
			os = new FileOutputStream(file);
			os.write(buffer);
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
	}

	public static void writeFileString(String fileName,String content) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		File distFile = new File(fileName);
		try {
			if (distFile.exists()) {
				distFile.delete();
			}
			distFile.createNewFile();
			bufferedReader = new BufferedReader(new StringReader(content));
			bufferedWriter = new BufferedWriter(new FileWriter(distFile));
			char buf[] = new char[BUFFER_SIZE];
			int len;
			while ((len = bufferedReader.read(buf)) != -1) {
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件夹读取字符串
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readFileString(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuilder builder = new StringBuilder();
			while (reader.ready()) {
				builder.append(reader.readLine());
			}
			reader.close();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 遍历文件夹
	 * 
	 * @param fileDir
	 * @param list
	 * @param suffix
	 * @return
	 */
	public static List<File> searchBySuffix(String fileDir, List<File> list,
			String... suffix) {
		if (null == list)
			list = new ArrayList<File>();
		File file = new File(fileDir);
		if (file.isDirectory()) {
			File[] childs = file.listFiles();
			if (childs != null)
				for (int i = 0; i < childs.length; i++) {
					if (childs[i].isDirectory()) {
						searchBySuffix(childs[i].getAbsolutePath(), list,
								suffix);
					} else {
						for (int j = 0; j < suffix.length; j++) {
							if (childs[i].getName().endsWith(suffix[j])) {
								list.add(childs[i]);
							}
						}

					}
				}
		}
		return list;
	}

	/**
	 * 文件是否保存成功
	 * 
	 * @param content
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean isSaveSuccess(InputStream content, String fileDir,
			String fileName) {
		FlushedInputStream is = null;
		FileOutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			deleteFile(fileDir, fileName);
			File file = createFile(fileDir, fileName);
			is = new FlushedInputStream(new BufferedInputStream(content,
					IO_BUFFER_SIZE));
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os, IO_BUFFER_SIZE);
			int byteRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((byteRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, byteRead);
			}
			bos.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		deleteFile(fileDir, fileName);
		return false;
	}

	private static class FlushedInputStream extends FilterInputStream {

		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int by_te = read();
					if (by_te < 0) {
						break;
					} else {
						bytesSkipped = 1;
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}

	/**
	 * 获取文件的大小
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSize(String fileName) {
		File file = new File(fileName);
		if (!file.exists())
			return null;
		long length = file.length();
		float SIZE_BT = 1024L;
		float SIZE_KB = SIZE_BT * 1024.0f;
		float SIZE_MB = SIZE_KB * 1024.0f;
		float SIZE_GB = SIZE_MB * 1024.0f;
		int SACLE = 2;
		if (length >= 0 && length < SIZE_BT) {
			return (double) (Math.round(length * 10) / 10.0) + "B";
		} else if (length >= SIZE_BT && length < SIZE_KB) {
			return (double) (Math.round((length / SIZE_BT) * 10) / 10.0) + "KB";
		} else if (length >= SIZE_KB && length < SIZE_MB) {
			return (double) (Math.round((length / SIZE_KB) * 10) / 10.0) + "MB";
		} else if (length >= SIZE_MB && length < SIZE_GB) {
			BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
					.toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			return result + "GB";
		} else {
			BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
					.toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			return result + "TB";
		}
	}

}
