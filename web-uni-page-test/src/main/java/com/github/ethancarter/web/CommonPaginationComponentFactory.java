package com.github.ethancarter.web;

import cn.idev.excel.FastExcel;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.result.set.PaginationResultSet;
import com.github.ethancarter.unipage.result.set.PaginationResultSetMetadata;
import com.github.ethancarter.unipage.result.set.PaginationRow;
import com.github.ethancarter.unipage.web.component.DefaultPaginationComponent;
import com.github.ethancarter.unipage.web.component.DefaultPaginationComponentFactory;
import com.github.ethancarter.unipage.web.component.PaginationComponent;
import com.github.ethancarter.unipage.web.component.PaginationExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommonPaginationComponentFactory extends DefaultPaginationComponentFactory {

    private static final Logger log = LoggerFactory.getLogger(CommonPaginationComponentFactory.class);
    private final DefaultPaginationComponent defaultPaginationComponent = new DefaultPaginationComponent();

    public CommonPaginationComponentFactory() {
        defaultPaginationComponent.setExporter(new DefaultExporter());
    }

    @Override
    public PaginationComponent getDefaultPaginationComponent() {
        return defaultPaginationComponent;
    }

    private static class DefaultExporter implements PaginationExporter {

        @Override
        public void exports(PaginationResultSet resultSet, HttpServletRequest request, HttpServletResponse response) {

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=organizations.xlsx");

            PaginationResultSetMetadata metadata = resultSet.getMetadata();
            int columnCount = metadata.getColumnCount();
            List<List<String>> headers = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                headers.add(Collections.singletonList(metadata.getColumnMetadata(i).getColumnName()));
            }
            List<List<Object>> rows = new ArrayList<>();
            for (PaginationRow paginationRow : resultSet) {
                List<Object> row = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    row.add(paginationRow.getObject(i));
                }
                rows.add(row);
            }
            try {
                FastExcel.write(response.getOutputStream())
                        .head(headers)
                        .sheet()
                        .doWrite(rows);
            } catch (IOException e) {
                throw new PaginationException("Failed to export data to Excel", e);
            }
        }
    }
}
