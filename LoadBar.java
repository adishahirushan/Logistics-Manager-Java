public class LoadBar {
    public static void main(String[] args) {
        int total = 100; // total bar length
        for (int i = 0; i <= total; i++) {
            int percent = (i * 100) / total;
            StringBuilder bar = new StringBuilder();
            bar.append("\r[");
            for (int j = 0; j < total; j++) {
                if (j < i) bar.append("=");
                else if (j == i) bar.append(">");
                else bar.append(" ");
            }
            bar.append("] ").append(percent).append("%");
            System.out.print(bar);

            try {
                Thread.sleep(100); // delay in milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupt status
                System.out.println("\nLoading interrupted.");
                break;
            }
        }
        System.out.println("\nLoad complete!");
    }
}
