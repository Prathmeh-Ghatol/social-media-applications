package com.blogApplication.fileHandeling.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.blogApplication.config.BlogConstatnt;
import com.blogApplication.fileHandeling.IFileHandeler;
import com.blogApplication.utils.Maps;

@Component
public class FileHandeler implements IFileHandeler{
	@Autowired
	Maps map;
	@Override
	public String uploadFile(String Path,MultipartFile file)throws IOException {
		File f=new File(Path);
		if(!f.exists()) {
			f.mkdir();
			}
		
		String FileName=file.getOriginalFilename();
		int extentionIndex=0;
		for(int i=0;i<FileName.length();i++) {
			if(FileName.charAt(i) =='.') {
				extentionIndex=i;
				break;
			}
		}
		String randmoFileName=UUID.randomUUID().toString();
		randmoFileName.concat(FileName).substring(extentionIndex);
		String Path_Saver=Path+ File.pathSeparator +randmoFileName;
		
		Files.copy(file.getInputStream(), Paths.get(Path_Saver));
		map.imageName_id.put(FileName, randmoFileName);
		System.out.print("dddddddddddddddddd" + randmoFileName);
		System.out.print(this.map.imageName_id.size());
		return FileName;
		
		
	
	}

}
