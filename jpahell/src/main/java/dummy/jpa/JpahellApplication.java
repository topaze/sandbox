package dummy.jpa;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import dummy.jpa.model.Releve;
import dummy.jpa.repo.ReleveRepo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan("dummy.jpa.repo")
@ImportResource( { "classpath:spring/applicationContext.xml" } )
public class JpahellApplication extends Application {

    private TableView<Releve> table = new TableView<Releve>();
    private final ObservableList<Releve> data = FXCollections.observableArrayList();

    final HBox hb = new HBox();

    private static String[] args;

    //    private ConfigurableApplicationContext context;
    private ReleveRepo repo;

    public JpahellApplication() {
    }


    @Bean
    public CommandLineRunner hello(ReleveRepo repo) {

	Releve rel = new Releve();
	rel.setType("Elec");
	Random rand = new Random();
	rel.setIndex(rand.nextInt(100000));
	DecimalFormat df = new DecimalFormat("00");
	rel.setDate("201511" + df.format(rand.nextInt(31)));
	repo.save(rel);

	return (args) -> {
	    repo.findAll().forEach(
		    r->
		    System.err.println(String.format(
			    "%s, %s, %s", r.getDate(), r.getType(), r.getIndex() 
			    ))
		    );
	};

    }



    @Override
    public void start(Stage stage) {
	ConfigurableApplicationContext context = SpringApplication.run(JpahellApplication.class, args);
	repo = context.getBean(ReleveRepo.class);

	Scene scene = new Scene(new Group());
	stage.setTitle("Table View Sample");
	stage.setWidth(450);
	stage.setHeight(550);

	final Label label = new Label("Address Book");
	label.setFont(new Font("Arial", 20));

	table.setEditable(true);

	TableColumn typeCol = new TableColumn("Type");
	typeCol.setMinWidth(100);
	typeCol.setCellValueFactory(
		new PropertyValueFactory<Releve, String>("Type"));

	TableColumn dateCol = new TableColumn("Date");
	dateCol.setMinWidth(100);
	dateCol.setCellValueFactory(
		new PropertyValueFactory<Releve, String>("Date"));

	TableColumn indexCol = new TableColumn("Index");
	indexCol.setMinWidth(200);
	indexCol.setCellValueFactory(
		new PropertyValueFactory<Releve, Integer>("Index"));

	
	repo.findAll().forEach(r->data.add(r));	
	table.setItems(data);
	
	
	table.getColumns().addAll(typeCol, dateCol, indexCol);

	final TextField addType = new TextField();
	addType.setPromptText("Type");
	addType.setMaxWidth(typeCol.getPrefWidth());

	final TextField addDate = new TextField();
	addDate.setMaxWidth(dateCol.getPrefWidth());
	addDate.setPromptText("Date");

	final TextField addIndex = new TextField();
	addIndex.setMaxWidth(indexCol.getPrefWidth());
	addIndex.setPromptText("Index");

	final Button addButton = new Button("Add");
	addButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent e) {


		//		System.err.println(repo==null);
		//		System.err.println(repo.count());
		//		repo.findAll().forEach(
		//			r->
		//			System.err.println(String.format(
		//				"%s, %s, %s", r.getDate(), r.getType(), r.getIndex() 
		//				))
		//			);

		Releve rel = new Releve(
			addType.getText(),
			addDate.getText(),
			Integer.parseInt(addIndex.getText()));
		repo.save(rel);

		data.add(new Releve(
			addType.getText(),
			addDate.getText(),
			Integer.parseInt(addIndex.getText())));
		addType.clear();
		addDate.clear();
		addIndex.clear();
	    }
	});

	hb.getChildren().addAll(addType, addDate, addIndex, addButton);
	hb.setSpacing(3);

	final VBox vbox = new VBox();
	vbox.setSpacing(5);
	vbox.setPadding(new Insets(10, 0, 0, 10));
	vbox.getChildren().addAll(label, table, hb);

	((Group) scene.getRoot()).getChildren().addAll(vbox);

	stage.setScene(scene);
	stage.show();
    }

    public static void main(String[] args) {	
	JpahellApplication.args = args;	
	launch(args);
    }

}
