package com.tw;

import java.util.List;

import static com.tw.util.IndentUtil.indent;
import static java.lang.String.format;

public interface Reportable {
    String report();

    static String gatherReport(String reportableInfo, List<Storable> storables) {
        StringBuilder stringBuilder = new StringBuilder(reportableInfo);

        for (Storable storable : storables) {
            stringBuilder.append(format("\n%s", indent(storable.report())));
        }
        return stringBuilder.toString();
    }
}
