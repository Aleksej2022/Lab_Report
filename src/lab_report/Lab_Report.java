package lab_report;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Scanner;

public class Lab_Report extends JFrame {

    static int titleWord_counter = 0;
    static int statement_problem = 0;
    static int hypothesis_counter = 0;
    static int materials_counter = 0;
    static int method_counter = 0;
    static int conclusion_counter = 0;

    static String lab_reportTitle;
    static Document document;
    static PdfWriter writer;

    static String title_acceptStatus = "";
    static String problem_acceptStatus = "";
    static String hypothesis_acceptStatus = "";
    static String material_acceptStatus = "";
    static String method_acceptStatus = "";
    static String conclusion_acceptStatus = "";

    static String material;
    static String method;
    static String add4;
    static String add5;

    static String series1_name = "";


    //This is the part that generates and displays the line chart according to the user's dataset entry
    public Lab_Report(String title, String chart_title, String XAxis, String YAxis) {
        super(title);
        //Create dataset
        DefaultCategoryDataset dataset = createDataset();
        //Create chart
        JFreeChart chart = ChartFactory.createLineChart(chart_title, XAxis, YAxis, dataset);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultCategoryDataset createDataset() {
        // Create two scanner objects
        Scanner numObj = new Scanner(System.in);
        Scanner textObj = new Scanner(System.in);

        System.out.println("Enter the name of the series");
        String data = textObj.nextLine();
        series1_name = data;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        System.out.println(Ansi_Colors.RED_UNDERLINED + series1_name + "series");
        for (int y = 0; y < 5; y++) {
            System.out.println(Ansi_Colors.BLUE_BOLD + "Enter the y axis value (this should be a number value)");
            int y_axis = numObj.nextInt();
            System.out.println(Ansi_Colors.BLUE_BOLD + "Enter the y axis value (this should be a text value)");
            String x_axis = textObj.nextLine();

            dataset.addValue(y_axis, series1_name, x_axis);
        }
        return dataset;
    }

    public static void main(String[] args) throws Exception {
        Scanner number = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        System.out.println(Ansi_Colors.RED_BOLD + "Welcome to the science lab report generator system!!!");
        Thread.sleep(1000);
        System.out.println(Ansi_Colors.BLUE + "Would you like to start creating a lab report(Yes or No)?");
        String response = text.nextLine();
        while (response.equals("Yes")) {
            System.out.println(Ansi_Colors.RESET + "Please enter your name");
            String name = text.nextLine();
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Welcome " + name + "let's get started");

            StringBuilder title = new StringBuilder(name.toUpperCase() + "`S LAB REPORT");
            lab_reportTitle = name + "`s Lab Report" + ".pdf";
            String user_name = title.toString();
            Thread.sleep(1000);

            //Title of the lab experiment needs to be entered here
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("We will start off by entering the title of your experiment!");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Title Guideline: ->\nProvide a brief concise, yet descriptive title");
            System.out.println("Title Word Count Limit: 100(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Your Title: ->");
            String user_title = text.nextLine();
            String add = " " + user_title;
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            StringBuilder user_titleCount = new StringBuilder(user_title);
            //Counting the number of words in the title information the user entered
            for (int y = 0; y < add.length(); y++) {
                if (add.charAt(y) == ' ') {
                    titleWord_counter = titleWord_counter + 1;
                }
            }

            int check_titleCapacity = user_titleCount.capacity() - 16;
            while (check_titleCapacity > 600 || check_titleCapacity < 140) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 140 to 600 characters\nYour word count is: -> " + titleWord_counter + "\nYour character count is: -> " + check_titleCapacity);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter Your Title again: ->");
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                user_title = text.nextLine();
                add = " " + user_title;
                titleWord_counter = 0;
                user_titleCount.setLength(0);
                user_titleCount = new StringBuilder(user_title);
                check_titleCapacity = user_titleCount.capacity() - 16;

                //Counting the number of words in the title information the user entered
                for (int y = 0; y < add.length(); y++) {
                    if (add.charAt(y) == ' ') {
                        titleWord_counter = titleWord_counter + 1;
                    }
                }
            }

            if (check_titleCapacity >= 140 && check_titleCapacity <= 600) {
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + titleWord_counter);
                title_acceptStatus = "valid";
            }
            Thread.sleep(1000);
            //Statement of the problem for lab experiment needs to be entered here
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("Next you will need to enter the statement problem or research question for your experiment");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Statement Of Problem Guidelines: ->\nWhat questions are you trying to answer\nProvide any preliminary background information about the subject");
            System.out.println("Statement Of Problem Word Count Limit: 100(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter Your Statement Of Problem: ->");
            String experiment_problem = text.nextLine();
            String add2 = " " + experiment_problem;
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            StringBuilder problem_count = new StringBuilder(experiment_problem);
            //Counting the number of words in the statement of problem information the user entered
            for (int y = 0; y < add2.length(); y++) {
                if (add2.charAt(y) == ' ') {
                    statement_problem = statement_problem + 1;
                }
            }

            int check_problemCapacity = problem_count.capacity() - 16;
            while (check_problemCapacity > 600 || check_problemCapacity < 140) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 140 to 600 characters\nYour word count is: -> " + statement_problem + "\nYour character count is: -> " + check_problemCapacity);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter your statement Of problem again: ->");
                 experiment_problem = text.nextLine();
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                add2 = " " + experiment_problem;
                 statement_problem = 0;
                problem_count.setLength(0);
                 problem_count = new StringBuilder(experiment_problem);
                check_problemCapacity = problem_count.capacity() - 16;
                //Counting the number of words in the statement of problem information the user entered
                for (int y = 0; y < add2.length(); y++) {
                    if (add2.charAt(y) == ' ') {
                        statement_problem = statement_problem + 1;
                    }
                }
            }

            if (check_problemCapacity >= 140 && check_problemCapacity <= 600){
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + statement_problem);
                problem_acceptStatus = "valid";
            }
            Thread.sleep(1000);

            //Hypothesis of the lab experiment
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("Next you will need to enter your hypothesis  for the lab experiment");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Hypothesis Guidelines: ->\nWrite a prediction of what you think will be the results of the experiment\nMake sure the prediction you have written is a complete sentence\nMake sure the prediction statement is testable\nFinally, the prediction statement should reference the dependent, independent and control variables");
            System.out.println("Hypothesis Problem Word Count Limit: 100(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter your hypothesis: ->");
            String user_hypothesis = text.nextLine();
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            String add3 = " " + user_hypothesis;

            StringBuilder hypothesis_count = new StringBuilder(user_hypothesis);
            //Counting the number of words in the hypothesis that the user entered
            for (int y = 0; y < add3.length(); y++) {
                if (add3.charAt(y) == ' ') {
                    hypothesis_counter = hypothesis_counter + 1;
                }
            }

            int check_hypothesisCapacity = hypothesis_count.capacity() - 16;
            while (check_hypothesisCapacity > 600 || check_hypothesisCapacity < 140) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 140 to 600 characters\nYour word count is: -> " + hypothesis_counter + "\nYour character count is: -> " + check_hypothesisCapacity);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter your hypothesis again: ->");
                user_hypothesis = text.nextLine();
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                 add3 = " " + user_hypothesis;
                hypothesis_counter = 0;
                hypothesis_count.setLength(0);
                 hypothesis_count = new StringBuilder(user_hypothesis);
                check_hypothesisCapacity = hypothesis_count.capacity() - 16;
                //Counting the number of words in the hypothesis that the user entered
                for (int y = 0; y < add3.length(); y++) {
                    if (add3.charAt(y) == ' ') {
                        hypothesis_counter = hypothesis_counter + 1;
                    }
                }
            }

            if (check_hypothesisCapacity >= 140 && check_hypothesisCapacity <= 600){
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + hypothesis_counter);
                hypothesis_acceptStatus = "valid";
            }

            Thread.sleep(1000);
            //Materials used for the lab experiment

            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("Now you will need to write the materials used for the lab experiment");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Materials Guideline: ->\nMake a list of the materials used for the lab experiment ");
            System.out.println("Material Word Count Limit: 150(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.CYAN_BOLD_BRIGHT + "How many materials would you like to list out?");
            int user_materials = number.nextInt();
            List list1 = new List(List.ORDERED);
            list1.setFirst(1);
            for (int i = 0; i < user_materials; i++){
                System.out.println("Enter material number " + (i + 1));
                material = text.nextLine();
                list1.add(material);
                add4 = " " + (i + 1) + "." + " " + material;
                for (int y = 0; y < add4.length(); y++){
                    if (add4.charAt(y) == ' '){
                        materials_counter = materials_counter + 1;
                    }
                }
            }
            while (materials_counter > 150 || materials_counter < 25) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 75 to 150 words\nYour word count is: -> " + materials_counter);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                materials_counter = 0;

                System.out.println(Ansi_Colors.CYAN_BOLD_BRIGHT + "How many materials would you like to list out?");
                 user_materials = number.nextInt();
                 list1 = new List(List.ORDERED);
                list1.setFirst(1);
                for (int i = 0; i < user_materials; i++){
                    System.out.println("Enter material number " + (i + 1));
                    material = text.nextLine();
                    list1.add(material);
                    add4 = " " + (i + 1) + "." + " " + material;
                    for (int y = 0; y < add4.length(); y++){
                        if (add4.charAt(y) == ' '){
                            materials_counter = materials_counter + 1;
                        }
                    }
                }
            }

            if (materials_counter >= 25 && materials_counter < 150){
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + materials_counter);
                material_acceptStatus = "valid";
            }
                Thread.sleep(1000);

            //Methods for the lab procedure

            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("Now you will need to write the methods used for the lab experiment");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Methods Guideline: ->\nWrite a list of steps that explains what you did in the lab experiment");
            System.out.println("Method Word Count Limit: 150(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.CYAN_BOLD_BRIGHT + "How many method steps would you like to list out?");
            int user_methods = number.nextInt();
            List list2 = new List(List.ORDERED);
            list2.setFirst(1);
            for (int i = 0; i < user_methods; i++){
                System.out.println("Enter method number " + (i + 1));
                method = text.nextLine();
                list2.add(method);
                add5 = " " + (i + 1) + "." + " " + method;
                for (int y = 0; y < add5.length(); y++){
                    if (add5.charAt(y) == ' '){
                        method_counter = method_counter + 1;
                    }
                }
            }
            while (method_counter > 150 || method_counter < 25) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 75 to 150 words\nYour word count is: -> " + method_counter);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                method_counter = 0;

                System.out.println(Ansi_Colors.CYAN_BOLD_BRIGHT + "How many method steps would you like to list out?");
                user_methods = number.nextInt();
                list2 = new List(List.ORDERED);
                list2.setFirst(1);
                for (int i = 0; i < user_methods; i++){
                    System.out.println("Enter method number " + (i + 1));
                    method = text.nextLine();
                    list2.add(method);
                    add5 = " " + (i + 1) + "." + " " + method;
                    for (int y = 0; y < add5.length(); y++){
                        if (add5.charAt(y) == ' '){
                            method_counter = method_counter + 1;
                        }
                    }
                }
            }

            if (method_counter >= 25 && method_counter < 150){
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + method_counter);
                method_acceptStatus = "valid";
            }

            //Adding an image of the lab experiment set up
            Thread.sleep(1000);

            System.out.println("Enter the path of the image for your lab experiment set up\nFor example: -> C:\\Users\\Alisia Habibi\\Desktop\\Java\\Lab_Report Generator\\image1.png\nYou need to add the image name and extension(jpg, png) as you have seen in the example");
            String image_setup = text.nextLine();
            Image image = Image.getInstance(image_setup);

            Thread.sleep(1000);

            //Data results for the lab experiment

            System.out.println(Ansi_Colors.RESET + "Enter the main title of your lab experiment\n A line chart will be created on your lab experiment data results");
            String chart_main = text.nextLine();
            System.out.println("Enter a title that will be placed on the line chart");
            String chart_title = text.nextLine();
            System.out.println("Enter the x axis title");
            String x_axis = text.nextLine();
            System.out.println("Enter the y axis title");
            String y_axis = text.nextLine();

            Lab_Report results = new Lab_Report(chart_main,chart_title,x_axis,y_axis);
            results.setAlwaysOnTop(true);
            results.pack();
            results.setSize(500,500);
            results.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            results.setVisible(true);

            //Taking a screenshot image of the line chart produced
            Thread.sleep(1000);

            try {
                Robot r = new Robot();
                System.out.println("Enter the file location where the image can be stored");
                String path = text.nextLine();
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT +"Notification: -> Please open on the line chart image\nWait for three seconds so the screenshot can be taken");
                Thread.sleep(3000);
                Dimension dimension = new Dimension(500,500);
                Rectangle capture = new Rectangle(dimension);
                BufferedImage Image = r.createScreenCapture(capture);
                ImageIO.write(Image,"png",new File(path));
                System.out.println("The image has been successfully saved!");

            }catch (Exception E){
                E.printStackTrace();
            }

            System.out.println(Ansi_Colors.BLACK_BOLD + "Enter the file location for the line chart image");
            String data_result = text.nextLine();
            Image image2 = Image.getInstance(data_result);


            //Conclusion of the lab experiment

            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            System.out.println("Finally,you will need to enter the conclusion  for your lab experiment");
            System.out.println(Ansi_Colors.PURPLE_BOLD_BRIGHT + "Conclusion Guidelines: ->\nYou need state if you accept or reject your hypothesis\nExplain why you accepted or rejected your hypothesis using the data results of your lab experiment");
            System.out.println("Conclusion Word Count Limit: 100(1 word = approximately 6 characters)");
            System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter your conclusion: ->");
            String user_conclusion = text.nextLine();
            System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            String add6 = " " + user_conclusion;

            StringBuilder conclusion_count = new StringBuilder(user_conclusion);
            //Counting the number of words in the hypothesis that the user entered
            for (int y = 0; y < add6.length(); y++) {
                if (add6.charAt(y) == ' ') {
                    conclusion_counter = conclusion_counter + 1;
                }
            }

            int check_conclusionCapacity = conclusion_count.capacity() - 16;
            while (check_conclusionCapacity> 600 || check_conclusionCapacity < 140) {
                System.out.println(Ansi_Colors.RED_BRIGHT + "You have entered more or less number of words\nYou need to enter between 140 to 600 characters\nYour word count is: -> " + conclusion_counter + "\nYour character count is: -> " + check_conclusionCapacity);
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Enter your conclusion again: ->");
                user_conclusion = text.nextLine();
                System.out.println(Ansi_Colors.RED_BRIGHT + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                add6 = " " + user_conclusion;
                conclusion_counter = 0;
                conclusion_count.setLength(0);
                conclusion_count = new StringBuilder(user_conclusion);
                check_conclusionCapacity = conclusion_count.capacity() - 16;
                //Counting the number of words in the hypothesis that the user entered
                for (int y = 0; y < add6.length(); y++) {
                    if (add6.charAt(y) == ' ') {
                        conclusion_counter = conclusion_counter + 1;
                    }
                }
            }

            if (check_conclusionCapacity >= 140 && check_conclusionCapacity <= 600){
                System.out.println(Ansi_Colors.RED_BOLD_BRIGHT + "You have entered an acceptable number of words!");
                System.out.println("Your word count is: " + conclusion_counter);
                conclusion_acceptStatus = "valid";
            }

            if (title_acceptStatus.equals("valid") && problem_acceptStatus.equals("valid") && hypothesis_acceptStatus.equals("valid") && method_acceptStatus.equals("valid") && conclusion_acceptStatus.equals("valid")){
                try {
                    document = new Document();
                    writer = PdfWriter.getInstance(document,new FileOutputStream(lab_reportTitle));
                    document.open();
                    Paragraph centerAlign = new Paragraph(user_name);
                    centerAlign.setAlignment(Element.ALIGN_CENTER);
                    document.add(centerAlign);
                    Chunk textUnderline = new Chunk(user_title);
                    textUnderline.setUnderline(0.8f, -1f);
                    Paragraph centerAlign2 = new Paragraph(textUnderline);
                    centerAlign2.setAlignment(Element.ALIGN_CENTER);
                    document.add(centerAlign2);
                    document.add(new Paragraph("\nStatement Of Problem: ->\n" + experiment_problem));
                    document.add(new Paragraph("\nHypothesis: ->\n" + user_hypothesis));
                    document.add(new Paragraph("\nMaterials: ->\n"));
                    document.add(list1);
                    document.add(new Paragraph("\nMethods: ->\n"));
                    document.add(list2);

                    document.newPage();
                    document.add(new Paragraph("\nLab Setup: ->\n"));
                    document.add(image);

                    document.newPage();
                    document.add(new Paragraph("\nData Results: ->\n"));
                    document.add(image2);

                    document.newPage();
                    document.add(new Paragraph("\nConclusion: ->\n" + user_conclusion));
                    document.close();
                    writer.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println(Ansi_Colors.RED_BOLD + "Some of the information you have entered is invalid");
            }

             titleWord_counter = 0;
             statement_problem = 0;
             hypothesis_counter = 0;
             materials_counter = 0;
             method_counter = 0;
             conclusion_counter = 0;

            System.out.println(Ansi_Colors.BLUE + "Would you like to start creating a lab report(Yes or No)?");
             response = text.nextLine();
        }

        if (response.equals("No")){
            System.out.println(Ansi_Colors.BLACK_BOLD_BRIGHT + "Okay, thank you for using the lab report generator");
        }
    }
}
