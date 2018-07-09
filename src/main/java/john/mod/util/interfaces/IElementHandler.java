package john.mod.util.interfaces;

import john.mod.BioElements;

public interface IElementHandler {
    BioElements getElement();
    void setElement(BioElements element);
    void removeElement();
}
