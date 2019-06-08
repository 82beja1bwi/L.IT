package controllers;



import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class AuftragsinfoController {
	
	@FXML private TitledPane titledPane_AI_rechner1;
	
	@FXML private Label lbl_AI_AuftragsNr;
	@FXML private Label lbl_AI_status;
	@FXML private Label lbl_AI_lieferdatum;
	@FXML private Label lbl_AI_bestelldatum;
	@FXML private Label lbl_AI_kunde;
	@FXML private Label lbl_AI_kundentyp;
	@FXML private Label lbl_AI_kundenNr;
	@FXML private Label lbl_AI_kundenEMail;
	
	@FXML private Accordion accordion_AI_rechner; //"TitledPane" hinzufügen
	
}
