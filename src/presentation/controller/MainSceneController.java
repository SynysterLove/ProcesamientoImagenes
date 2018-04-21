package presentation.controller;

import core.provider.PresenterProvider;
import core.provider.ViewProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
    public Button applyChangesButton;
    @FXML
    public TextField pixelX;
    @FXML
    public TextField pixelY;
    @FXML
    public TextField valueR;
    @FXML
    public TextField valueG;
    @FXML
    public TextField valueB;

    public CustomImageView customImageView;

    private final MainPresenter mainPresenter;

    public MainSceneController() {

        this.mainPresenter = PresenterProvider.provideImageSelectionPresenter(this);
        ViewProvider.setMainView(this);
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
    public void applyChanges() {
        this.mainPresenter.onApplyChanges();
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
    public void calculateImagesOperations() {
        this.mainPresenter.onCalculateImagesOperations();
    }

    @FXML
    public void calculateNegativeImage() {
        this.mainPresenter.onCalculateNegativeImage();
    }

    @FXML
    public void threshold() {
        this.mainPresenter.onThreshold();
    }

    @FXML
    public void contrast() {
        this.mainPresenter.onContrast();
    }

    @FXML
    public void compressDynamicRange() {
        this.mainPresenter.onCompressDynamicRange();
    }

    @FXML
    public void gammaPowerFunction() {
        this.mainPresenter.onGammaPowerFunction();
    }

    @FXML
    public void generateExponentialRandomNumber() {
        this.mainPresenter.onGenerateExponentialRandomNumber();
    }

    @FXML
    public void generateRayleighRandomNumber() {
        this.mainPresenter.onGenerateRayleighRandomNumber();
    }

    @FXML
    public void generateGaussianRandomNumber() {
        this.mainPresenter.onGenerateGaussianRandomNumber();
    }

    @FXML
    public void close() {
        Platform.exit();
    }

    @FXML
    public void createImageHistogram() {
        this.mainPresenter.onCreateImageHistogram();
    }

    @FXML
    public void createEqualizedImage() {
        this.mainPresenter.onCreateEqualizedImageOnce();
    }

    @FXML
    public void createImageEqualizedTwice() {
        this.mainPresenter.onCreateEqualizedImageTwice();
    }

    @FXML
    public void applySaltAndPepperNoise() { this.mainPresenter.onApplySaltAndPepperNoise(); }

    @FXML
    public void generateExponentialNoiseSyntheticImage() {
        this.mainPresenter.onGenerateExponentialNoiseSyntheticImage();
    }

    @FXML
    public void generateRayleighNoiseSyntheticImage() {
        this.mainPresenter.onGenerateRayleighNoiseSyntheticImage();
    }

    @FXML
    public void generateGaussianNoiseSyntheticImage() {
        this.mainPresenter.onGenerateGaussianNoiseSyntheticImage();
    }

    @FXML
    public void createEqualizedImageByHistogram() {
        this.mainPresenter.onCreateEqualizedImageByHistogram();
    }

    @FXML
    public void createImageEqualizedTwiceByHistogram() {
        this.mainPresenter.onCreateEqualizedImageTwiceByHistogram();
    }

    @FXML
    public void applyAdditiveGaussianNoise() { this.mainPresenter.onApplyAdditiveGaussianNoise(); }

    @FXML
    public void applyMultiplicativeRayleighNoise() { this.mainPresenter.onApplyMultiplicativeRayleighNoise(); }

    @FXML
    public void applyMultiplicativeExponentialNoise() { this.mainPresenter.onApplyMultiplicativeExponentialNoise(); }

    @FXML
    public void onApplyMeanFilter() {
        this.mainPresenter.onApplyMeanFilter();
    }

    @FXML
    public void onApplyMedianFilter() {
        this.mainPresenter.onApplyMedianFilter();
    }
}
