package core.provider;

import core.action.edit.space_domain.GammaFunctionAction;
import presentation.controller.*;
import presentation.presenter.*;

public class PresenterProvider {

    private static MainPresenter mainPresenter;

    public static MainPresenter provideImageSelectionPresenter(MainSceneController mainSceneController) {
        if (mainPresenter == null) {
            mainPresenter = new MainPresenter(
                    mainSceneController,
                    ActionProvider.provideLoadImageAction(),
                    ActionProvider.provideGetImageAction(),
                    ActionProvider.providePutModifiedImageAction(),
                    ActionProvider.provideModifyPixelAction(),
                    ActionProvider.provideCalculateNegativeImageAction(),
                    ActionProvider.provideApplyThresholdAction(),
                    ActionProvider.provideCreateGradientAction(),
                    ActionProvider.provideObtainRGBChannelAction(),
                    ActionProvider.provideObtainHSVChannelAction(),
                    ActionProvider.provideCreateImageWithFigureAction(),
                    ActionProvider.provideCreateEqualizedGrayImageAction(),
                    PublishSubjectProvider.provideOnModifiedImagePublishSubject(),
                    ActionProvider.provideCompressDynamicRangeAction());

            return mainPresenter;
        }
        return mainPresenter;
    }

    public static ImageReportPresenter provideImageInformPresenter() {
        return new ImageReportPresenter(
                ActionProvider.provideGetModifiedImageAction(),
                ActionProvider.provideCreateImageInformAction());
    }

    public static SaveImagePresenter provideSaveImagePresenter(SaveImageController saveImageController) {
        return new SaveImagePresenter(saveImageController,
                ActionProvider.provideGetModifiedImageAction(),
                ActionProvider.provideSaveImageAction());

    }

    public static ImageHistogramPresenter provideImageHistogramPresenter(ImageHistogramSceneController view) {
        return new ImageHistogramPresenter(view,
                ActionProvider.provideGetImageAction(),
                ActionProvider.provideCreateImageHistogram());
    }

    public static ContrastScenePresenter provideContrastScenePresenter(ContrastSceneController contrastSceneController) {
        return new ContrastScenePresenter(contrastSceneController,
                ActionProvider.provideApplyContrastAction(),
                ActionProvider.provideGetImageAction(),
                ServiceProvider.provideGrayLevelStatisticsService(),
                PublishSubjectProvider.provideOnModifiedImagePublishSubject());
    }

    public static GammaScenePresenter provideGammaScenePresenter(GammaSceneController gammaSceneController) {
            return new GammaScenePresenter(gammaSceneController,
                    ActionProvider.provideGammaFunctionAction(),
                    PublishSubjectProvider.provideOnModifiedImagePublishSubject());
    }

}
