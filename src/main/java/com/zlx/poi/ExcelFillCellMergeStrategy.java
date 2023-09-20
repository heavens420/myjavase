package com.zlx.poi;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * EasyExcel 合并相邻相同的相同的列
 */
public class ExcelFillCellMergeStrategy implements CellWriteHandler {
    /**
     * 要合并的列
     */
    private List<Integer> mergeColumnIndex;
    /**
     * 开始合并的行
     */
    private int mergeRowIndex;

    /**
     * 要合并的行
     */
    private List<Integer> mergeRowsIndex;

    /**
     * 无参构造
     */
    public ExcelFillCellMergeStrategy() {
    }

    /**
     * 有参构造
     *
     * @param mergeRowIndex    开始合并的行
     * @param mergeColumnIndex 合并的列
     */
    public ExcelFillCellMergeStrategy(int mergeRowIndex, List<Integer> mergeColumnIndex) {
        this.mergeRowIndex = mergeRowIndex;
        this.mergeColumnIndex = mergeColumnIndex;
    }

    /**
     * 有参构造
     *
     * @param mergeRowIndex    开始合并的行
     * @param mergeColumnIndex 合并的列
     * @param mergeRowsIndex   合并的行
     */
    public ExcelFillCellMergeStrategy(int mergeRowIndex, List<Integer> mergeColumnIndex, List<Integer> mergeRowsIndex) {
        this.mergeRowIndex = mergeRowIndex;
        this.mergeColumnIndex = mergeColumnIndex;
        this.mergeRowsIndex = mergeRowsIndex;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }


    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //当前行
        final int curRowIndex = cell.getRowIndex();
        //当前列
        final int curColIndex = cell.getColumnIndex();
        //合并列（从指定行开始）
        if (curRowIndex > mergeRowIndex) {
            for (int i = 0; i < mergeColumnIndex.size(); i++) {
                if (curColIndex == mergeColumnIndex.get(i)) {
                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
                    break;
                }
            }
        }
        //合并行（从第一列开始）
        if (curColIndex > 0) {
            if (!ObjectUtils.isEmpty(mergeRowsIndex)) {
                for (int i = 0; i < mergeRowsIndex.size(); i++) {
                    if (curRowIndex == mergeRowsIndex.get(i)) {
                        mergeWithPrevCol(writeSheetHolder, cell, curRowIndex, curColIndex);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 当前单元格向上合并
     *
     * @param writeSheetHolder writeSheetHolder
     * @param cell             当前单元格
     * @param curRowIndex      当前行
     * @param curColIndex      当前列
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        //获取当前行的当前列的数据和上一行的当前列列数据，通过上一行数据是否相同进行合并
        final Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        final Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
        final Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() : preCell.getNumericCellValue();

        // 比较当前行的单元格与上一行是否相同，相同合并当前单元格与上一行
        if (curData.equals(preData)) {
            final Sheet sheet = writeSheetHolder.getSheet();
            final List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
                final CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged = true;
                }
            }
            // 若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                final CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex, curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }

    /**
     * 当前单元格向左合并
     *
     * @param writeSheetHolder writeSheetHolder
     * @param cell             当前单元格
     * @param curRowIndex      当前行
     * @param curColIndex      当前列
     */
    private void mergeWithPrevCol(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        //获取当前行的当前列的数据和上一列的当前行行数据，通过上一列数据是否相同进行合并
        final Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        final Cell preCell = cell.getSheet().getRow(curRowIndex).getCell(curColIndex - 1);
        final Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() : preCell.getNumericCellValue();

        // 比较当前行的单元格与上一列是否相同，相同合并当前单元格与上一列
        if (curData.equals(preData)) {
            final Sheet sheet = writeSheetHolder.getSheet();
            final List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
                final CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellRangeAddr.isInRange(curRowIndex, curColIndex - 1)) {
                    sheet.removeMergedRegion(i);
                    cellRangeAddr.setLastColumn(curColIndex);
                    sheet.addMergedRegion(cellRangeAddr);
                    isMerged = true;
                }
            }
            // 若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                final CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex, curRowIndex, curColIndex - 1, curColIndex);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }

}