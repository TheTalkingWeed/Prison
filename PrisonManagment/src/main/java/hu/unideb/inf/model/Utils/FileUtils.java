package hu.unideb.inf.model.Utils;

import hu.unideb.inf.model.PrisonerPac.Prisoner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> readCrimes(String filename){
        List<String> output = new ArrayList<>();
        try
        {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)
            {
                output.add(line);
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public static void creatPrisonerExcelFile(List<Prisoner> prisoners,String filename){
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sh = wb.createSheet("Queried prisoners");


            String[] columnNames = {"UniqueID","First name","Last name",
                    "Entrance date","Release date","Security level","Cell number","Crime"};

            Font hfont = wb.createFont();
            hfont.setBold(true);
            hfont.setFontHeightInPoints((short)12);
            hfont.setColor(IndexedColors.BLACK.index);

            CellStyle hStyle = wb.createCellStyle();
            hStyle.setFont(hfont);
            hStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            hStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

            Row hRow = sh.createRow(0);

            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = hRow.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(hStyle);
            }

           CreationHelper  ch = wb.getCreationHelper();
           CellStyle dataStyle = wb.createCellStyle();
           dataStyle.setDataFormat(ch.createDataFormat().getFormat("yyyy/MM/dd"));


           int rowindex = 1;
            for (Prisoner p:prisoners) {
                Row row = sh.createRow(rowindex++);
                row.createCell(0).setCellValue(p.getUniqueID());
                row.createCell(1).setCellValue(p.getFname());
                row.createCell(2).setCellValue(p.getLname());
                Cell edate = row.createCell(3);
                edate.setCellValue(p.getEntrancedate());
                edate.setCellStyle(dataStyle);
                Cell rdate = row.createCell(4);
                rdate.setCellValue(p.getReleasedate());
                rdate.setCellStyle(dataStyle);
                row.createCell(5).setCellValue(p.getSecuritylvl());
                row.createCell(6).setCellValue(p.getCellnumber());
                row.createCell(7).setCellValue(p.getCrime());
            }

            for (int i = 0; i < columnNames.length; i++) {
                sh.autoSizeColumn(i);
            }

            FileOutputStream fout = new FileOutputStream(filename);
            wb.write(fout);
            fout.close();
            wb.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
