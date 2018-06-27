import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class CanvasUI extends Canvas{
    //main timeline
    private Timeline timeline;
    private AnimationTimer timer;

    //variable for storing actual frame
    private Integer i=0;

    private String text = "Hello World";

    private GraphicsContext gc;
    private Paint backgroundColor = Color.WHITE;
    public CanvasUI(int width, int height) {
        super(width,height);
        gc = getGraphicsContext2D();
    }

    public void draw(){
        gc.setFill(backgroundColor);
        gc.fillRect(0,0,getWidth(),getHeight());
        gc.setFill(Color.BLUE);
        gc.fillRect(75,75,100,100);
        gc.fillText(text,50,50);
        gc.strokeLine(i,0,i,getHeight());

        var sb = new StringBuilder();
        sb.append(timeline.getCurrentRate()).append(" | ");
        sb.append(timeline.getCurrentTime()).append(" | ");
        sb.append(timeline.getCycleCount()).append(" | ");
        sb.append(timeline.getCycleDuration()).append(" | ");
        sb.append(timeline.getTargetFramerate()).append(" | ");
        gc.fillText(sb.toString(),60,20);
    }

    public void addTimeline(){
        //create a timeline for moving the circle

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        //You can add a specific action when each frame is started.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text = i.toString();
                i++;
                draw();
            }
        };

        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(2000);
        //one can add a specific action when the keyframe is reached
        EventHandler onFinished = (EventHandler<ActionEvent>) t -> {
            //reset counter
            i = 0;
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
    }

    public void start(){
        timeline.play();
        timer.start();
    }

    public void stop(){
        timeline.stop();
        timer.stop();
    }
}
