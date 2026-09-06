public class week1p3 {

    String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            String bmiFormatted = String.format("%.2f", bmi);
            System.out.println("Person " + (i + 1) + " | " + heights[i] + " | " + weights[i] + " | " + bmiFormatted + " | " + status);
        }
    }

    public static void main(String[] args) {
        week1p3 report = new week1p3();
        double[] heights = {1.75, 1.60, 1.68, 1.80, 1.55};
        double[] weights = {70, 90, 55, 95, 48};
        report.printWellnessReport(heights, weights);
    }
}
