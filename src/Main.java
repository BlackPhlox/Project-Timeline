import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    //META
    private static final String PROGRAM_NAME = "Timeline Visualizer";
    private static final String VERSION_NOTATION = "V";
    private static final double VERSION = 0.1;

    public static String getName(){
        return PROGRAM_NAME;
    }
    public static double getVersion(){
        return VERSION;
    }
    public static String getVersionName(){
        return VERSION_NOTATION + getVersion();
    }
    public static String getFullName(){
        return getName() + " " + getVersionName();
    }

    //Settings
    private final int width = 500, height = 500;
    private final boolean depthBuffering = true;
    private final SceneAntialiasing antialiasing = SceneAntialiasing.BALANCED;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        var canvas = new CanvasUI(width,height);
        root.getChildren().add(canvas);

        var add_btn = new Button("Add");
        add_btn.setOnAction(e -> canvas.addTimeline());
        var start_btn = new Button("Start");
        start_btn.setOnAction(e -> canvas.start());
        var stop_btn = new Button("Stop");
        stop_btn.setOnAction(e -> canvas.stop());

        var vbox = new VBox(add_btn,start_btn,stop_btn);
        root.getChildren().addAll(vbox);

        Scene scene = new Scene(root, width,height,depthBuffering,antialiasing);



        primaryStage.setScene(scene);
        primaryStage.setTitle(getFullName());
        primaryStage.show();
    }
}
