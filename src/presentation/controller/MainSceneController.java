package presentation.controller;

import core.provider.PresenterProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import presentation.presenter.MainPresenter;
import presentation.view.CustomImageView;

public class MainSceneController {

    @FXML
    public Group groupImageView;
    @FXML
    public ImageView imageView;
    @FXML
    public ImageView modifiedImageView;
    @FXML
    public TextField pixelX;
    @FXML
    public TextField pixelY;
    @FXML
    public TextField pixelValue;

    public CustomImageView customImageView;

    private final MainPresenter mainPresenter;

    public MainSceneController() {
        this.mainPresenter = PresenterProvider.provideImageSelectionPresenter(this);
    }

    @FXML
    //JavaFX invoke this method after constructor
    public void initialize() {
        this.mainPresenter.initialize();
    }

    @FXML
    public void openImage() {
        this.mainPresenter.onOpenImage();
    }

    @FXML
    public void saveModifiedImage() {
        this.mainPresenter.onSaveImage();
    }

    @FXML
    public void showGreyGradient() {
        this.mainPresenter.onShowGreyGradient();
    }

    @FXML
    public void showColorGradient() {
        this.mainPresenter.onShowColorGradient();
    }

    @FXML
    public void showRGBImageRedChannel() {
        this.mainPresenter.onShowRGBImageRedChannel();
    }

    @FXML
    public void showRGBImageGreenChannel() {
        this.mainPresenter.onShowRGBImageGreenChannel();
    }

    @FXML
    public void showRGBImageBlueChannel() {
        this.mainPresenter.onShowRGBImageBlueChannel();
    }

    @FXML
    public void showImageWithQuadrate() {
        this.mainPresenter.onShowImageWithQuadrate();
    }

    @FXML
    public void showImageWithCircle() {
        this.mainPresenter.onShowImageWithCircle();
    }

    @FXML
    public void showHueHSVChannel() {
        this.mainPresenter.onShowHueHSVChannel();
    }

    @FXML
    public void showSaturationHSVChannel() {
        this.mainPresenter.onShowSaturationHSVChannel();
    }

    @FXML
    public void showValueHSVChannel() {
        this.mainPresenter.onShowValueHSVChannel();
    }

    @FXML
    public void calculatePixelValue() {
        this.mainPresenter.onCalculatePixelValue();
    }

    @FXML
    public void modifyPixelValue() {
        this.mainPresenter.onModifyPixelValue();
    }

    @FXML
    public void showReport() {
        this.mainPresenter.onShowReport();
    }

    @FXML
    public void cutPartialImage() {
        this.mainPresenter.onCutPartialImage();
    }

    @FXML
    public void close() {
        Platform.exit();
    }
}