/**
 *  Copyright (C) 2015-2019  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextEditorLauncherForTests {
	
	private static String ROOT = "D:/TMP"; // Default value
	
	public static File getFile (String fileName) {
		if ( ROOT.endsWith("/") ) {
			return new File( ROOT + fileName); 
		}
		else {
			return new File( ROOT + "/" + fileName); 
		}
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	public static void print(String s) {
		System.out.print(s);
	}
	
	public static void init(String[] args) {
		if ( args.length > 0 ) {
			String directoryArg = args[0] ;
			println("File arg = " + directoryArg );
			File root = new File(directoryArg);
			if ( root.exists() ) {
				if ( root.isDirectory() ) {
					ROOT = directoryArg ;
				}
				else {
					println(directoryArg + " is not a directory ! ");
					System.exit(2);
				}
			}
			else {
				println(directoryArg + " doesn't exist ! ");
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		
		println("Starting, args.length = " + args.length );
		init(args);
		
		println("ROOT directory is " + ROOT);
		
		BufferedReader br = null;
		boolean quit = false ;
        try {

            br = new BufferedReader(new InputStreamReader(System.in));

            while (!quit) {

                print("Enter file name (or 'q' to quit) : ");
                String input = br.readLine();

                if ("q".equals(input) ) {
                    println("Exit!");
                    quit = true ;
                }
                else if ("".equals(input) ) {
                    println("No file");
                	TextEditorManager.editFile(null);
                }
                else {
                	File file = getFile(input);
                	println("Edit file " + file + "...");
                	
                	// Try to edit the file
                	TextEditorManager.editFile(file);
                }

                println("input : " + input);
                println("-----------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		println("End. ");
        System.exit(0);
	}

}
