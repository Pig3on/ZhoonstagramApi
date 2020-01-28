package vua.pavic.ZhoonstagramApi.utils;


import vua.pavic.ZhoonstagramApi.services.PigeonDetectionService;


public class PigeonVisitor implements  Visitor {
    PigeonDetectionService pigeonDetectionService;

    public PigeonVisitor(PigeonDetectionService pigeonDetectionService) {
        this.pigeonDetectionService = pigeonDetectionService;
    }

    private boolean isPigeon;
    @Override
    public void visit(VisitableImage image) {
        isPigeon = !(pigeonDetectionService.isPigeon(image.getImage()) < 0.95);
    }

    public boolean isPigeon(){
        return isPigeon;
    }
}
