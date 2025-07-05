import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class QuizManagementSystem {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/users.json"));

        JSONObject adminObject = (JSONObject) jsonArray.get(0);
        JSONObject studentObject = (JSONObject) jsonArray.get(1);
        String adminUsername = adminObject.get("username").toString();
        String adminPass = adminObject.get("password").toString();
        String studentUsername = studentObject.get("username").toString();
        String studentPass = studentObject.get("password").toString();
        String role = "";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (username.equals(adminUsername) && password.equals(adminPass)){
            role = adminObject.get("role").toString();
            System.out.println("Welcome admin! Please create new questions in the question bank.");
            addQuestions();
        }

        if (username.equals(studentUsername) && password.equals(studentPass)){
            role = studentObject.get("role").toString();
            System.out.println("Welcome "+ studentUsername  +" to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
            String inputChoice = scanner.nextLine();

            if(inputChoice.equals("s")){
                performQuiz();
            }
        }
    }

    public static void  addQuestions() throws IOException {
        JSONArray quizeQuestionArray = new JSONArray();
        JSONObject questionObject = new JSONObject();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input your question");
            String inputQuestion = scanner.nextLine();
            questionObject.put("question", inputQuestion);

            for(int i = 1; i < 5; i++) {
                System.out.println("Input option "+i);
                String option = scanner.nextLine();
                String optionNumber = "option " + i;
                questionObject.put(optionNumber, option);
            }

            System.out.println("What is the answer key?");
            String answerKeyInput = scanner.nextLine();
            questionObject.put("answerkey", answerKeyInput);

            quizeQuestionArray.add(questionObject);

            System.out.println("Saved successfully!");
            System.out.println("Do you want to add more questions? (press 's' to start, 'q' to quit)");

            String inputChoice = scanner.nextLine();

            if(inputChoice.equals("s")){
                continue;
            }
            if(inputChoice.equals("q")){
                try {
                    FileWriter fileWriter = new FileWriter("./src/main/resources/quiz.json");
                    fileWriter.write(quizeQuestionArray.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static void performQuiz() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/quiz.json"));
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;

        for (int i = 1; i < 11; i++){
            JSONObject randomQuestion = (JSONObject) jsonArray.get(random.nextInt(jsonArray.size()));
            String questionKey = "Question " + i;
            //jsonArray.get(questionKey,randomQuestion);
            System.out.println(questionKey +" -> "+randomQuestion.get("question"));

            for(int j = 1; j < 5; j++) {
                String key = "option " + j;
                System.out.println(j +". "+randomQuestion.get(key));
            }
            System.out.print("Student: ");
            String inputAnswer = scanner.nextLine();

            Object correctAnswer = randomQuestion.get("answerkey");

            if (inputAnswer.equals(correctAnswer.toString())){
                score++;
            }
        }

        if(score >= 8){
            System.out.println("Excellent! You have got " + score + " out of 10");
        }
        else if (score >= 5 && score < 8) {
            System.out.println("Good. You have got " + score + " out of 10");
        }
        else if (score >= 3 && score < 5) {
            System.out.println("Very poor! You have got "+ score + " out of 10");
        }
        else {
            System.out.println("Very sorry you are failed. You have got " + score + " out of 10");
        }
    }
}
