package com.nuclei.assignment3.constants;

public final class AppConstants {

  private AppConstants() {
  }

  public static final class QueryConstants {
    public static final String SELECT_ALL_ITEMS =
        "SELECT id, name, price, quantity, type, created_at, updated_at FROM items";

    private QueryConstants() {
    }
  }

  public static final class LogConstants {
    public static final String JOB_START =
        "========== Starting Item Tax Processing Job ==========";
    public static final String JOB_END =
        "========== Item Tax Processing Job Finished ==========";
    public static final String JOB_ERROR =
        "An error occurred during the tax processing job";
    public static final String REPORT_HEADER =
        "\n----------------------- TAX CALCULATION RESULTS -----------------------";
    public static final String REPORT_TABLE_BORDER =
        "\n+----------------------+-----------------------------------------------+";
    public static final String REPORT_FOOTER_BORDER =
        "\n+----------------------------------------------------------------------+";
    public static final String REPORT_SUMMARY_FORMAT =
        "\nSuccessfully processed %d items in %d milliseconds.";
    public static final String REPORT_SUMMARY_LINE =
        "\n-------------------------------------------------------------------";

    private LogConstants() {
    }
  }

  public static final class TaxRuleConstants {
    public static final double IMPORT_DUTY_RATE = 0.10; // 10%
    public static final double IMPORTED_SURCHARGE_FIRST_LIMIT = 100.0;
    public static final double IMPORTED_SURCHARGE_SECOND_LIMIT = 200.0;
    public static final double IMPORTED_SURCHARGE_FLAT_RATE_1 = 5.0;
    public static final double IMPORTED_SURCHARGE_FLAT_RATE_2 = 10.0;
    public static final double IMPORTED_SURCHARGE_PERCENTAGE_RATE = 0.05; // 5%
    public static final double MANUFACTURED_BASE_TAX_RATE = 0.125; // 12.5%
    public static final double MANUFACTURED_ADDITIONAL_TAX_RATE = 0.02;
    public static final double RAW_TAX_RATE = 0.125;

    private TaxRuleConstants() {
    }
  }
}