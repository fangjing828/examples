//package com.examples.encoder.zip;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.URI;
//import java.nio.file.FileSystem;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
///**
// * @author alex.fang
// * @date 2023/3/29
// */
//public class ZipEncoderTest {
//    static String SOURCE_FILE = "test.properties";
//    static String SOURCE_FILE_1 = "test1.properties";
//    static String ZIP_FILE = "compressed.zip";
//
//    static String ROOT_PATH;
//
//    @BeforeAll
//    static void beforeAll() {
//        ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        SOURCE_FILE = ROOT_PATH + "/" + SOURCE_FILE;
//        SOURCE_FILE_1 = ROOT_PATH + "/" + SOURCE_FILE_1;
//        ZIP_FILE = ROOT_PATH + "/" + ZIP_FILE;
//    }
//
//    @Test
//    void testZip() throws IOException{
//        testZipFile();
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(ZIP_FILE))) {
//            ZipEntry zipEntry;
//            while ((zipEntry = zis.getNextEntry()) != null) {
//                if (SOURCE_FILE.endsWith(zipEntry.getName())) {
//                    File newFile = new File(ROOT_PATH, zipEntry);
//                    if (zipEntry.isDirectory()) {
//                        if (!newFile.isDirectory() && !newFile.mkdirs()) {
//                            throw new IOException("Failed to create directory " + newFile);
//                        }
//                    } else {
//                        // fix for Windows-created archives
//                        File parent = newFile.getParentFile();
//                        if (!parent.isDirectory() && !parent.mkdirs()) {
//                            throw new IOException("Failed to create directory " + parent);
//                        }
//
//                        // write file content
//                        FileOutputStream fos = new FileOutputStream(newFile);
//                        int len;
//                        while ((len = zis.read(buffer)) > 0) {
//                            fos.write(buffer, 0, len);
//                        }
//                        fos.close();
//                    }
//                }
//
//                zipEntry = zis.getNextEntry();
//            }
//            zis.closeEntry();
//        }
//    }
//
//    @Test
//    void testZipFile() throws IOException {
//        try(FileOutputStream fos = new FileOutputStream(ZIP_FILE)) {
//            try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {
//                File fileToZip = new File(SOURCE_FILE);
//                try (FileInputStream fis = new FileInputStream(fileToZip)) {
//                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
//                    zipOut.putNextEntry(zipEntry);
//
//                    byte[] bytes = new byte[1024];
//                    int length;
//                    while ((length = fis.read(bytes)) >= 0) {
//                        zipOut.write(bytes, 0, length);
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    void testZipMultiFile() throws IOException {
//        try(FileOutputStream fos = new FileOutputStream(ZIP_FILE)) {
//            try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {
//                for (String filePath : new String[] {SOURCE_FILE, SOURCE_FILE_1}) {
//                    File fileToZip = new File(filePath);
//                    try (FileInputStream fis = new FileInputStream(fileToZip)) {
//                        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
//                        zipOut.putNextEntry(zipEntry);
//                        byte[] bytes = new byte[1024];
//                        int length;
//                        while ((length = fis.read(bytes)) >= 0) {
//                            zipOut.write(bytes, 0, length);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    void testUnzipFile() throws IOException {
//        testZipMultiFile();
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(ZIP_FILE))) {
//            ZipEntry zipEntry;
//            while ((zipEntry = zis.getNextEntry()) != null) {
//                System.out.println(zipEntry.getName());
//            }
//            zis.closeEntry();
//        }
//    }
//
//    @Test
//    void testAppendNewFileToZip() throws IOException {
//        testZipFile();
//        Map<String, String> env = new HashMap<>();
//        env.put("create", "true");
//
//        Path path = Paths.get(Paths.get(SOURCE_FILE_1).getParent() + "/compressed.zip");
//        URI uri = URI.create("jar:" + path.toUri());
//
//        try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
//            Path nf = fs.getPath("newFile3.txt");
//            Files.write(nf, Files.readAllBytes(Paths.get(SOURCE_FILE_1)), StandardOpenOption.CREATE);
//        }
//    }
//}
