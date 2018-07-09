package john.mod.util.handlers;

import john.mod.BioElements;
import john.mod.util.interfaces.IElementHandler;

public class DefaultElementHandler implements IElementHandler {
    private BioElements element;

    @Override
    public BioElements getElement() {
        return element;
    }

    @Override
    public void setElement(BioElements element) {
        this.element = element;
    }

    @Override
    public void removeElement() {
        this.element = BioElements.NONE;
    }
}