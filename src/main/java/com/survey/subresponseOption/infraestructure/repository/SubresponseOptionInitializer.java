public class SubresponseOptionInitializer {
    private AddResponseOptionUseCase addr;
    private DeleteUseCase delete;

    public SubresponseOptionInitializer(SubresponseOptionService subresponseOpotion) {
        this.addr = new AddResponseOptionUseCase(subresponseOption);
        this.delete = new DeleteUseCase(subresponseoasdf)
    }

    public add() {
        this.addResponseOption.execute();
    }

    public delete() {
        this.delete.execute();
    }
}
