package core.provider;

import presentation.presenter.ImageSelectionPresenter;
import presentation.presenter.ImageViewPresenter;

public class PresenterProvider {

    private static ImageSelectionPresenter imageSelectionPresenter;
    private static ImageViewPresenter imageViewPresenter;

    public static ImageSelectionPresenter provideImageSelectionPresenter() {
        if (imageSelectionPresenter == null) {
            imageSelectionPresenter = new ImageSelectionPresenter(
                    ActionProvider.provideLoadImageAction(),
                    ActionProvider.provideSetCurrentImagePathOnRepoAction(),
                    ActionProvider.provideGetCurrentImagePathAction(),
                    ActionProvider.provideGetImageAction(),
                    ActionProvider.provideSaveImageAction(),
                    ActionProvider.provideGetImageListAction());
            return imageSelectionPresenter;
        }
        return imageSelectionPresenter;
    }

    public static ImageViewPresenter provideImageViewPresenter() {
        if (imageViewPresenter == null) {
            imageViewPresenter = new ImageViewPresenter(
                    ActionProvider.provideGetImageAction(),
                    ActionProvider.provideModifyPixelAction(),
                    ActionProvider.provideCheckIfModifyingAction(),
                    ActionProvider.provideGetModifiedImageAction(),
                    ActionProvider.provideSaveChangesAction()
            );
            return imageViewPresenter;
        }
        return imageViewPresenter;
    }
}
