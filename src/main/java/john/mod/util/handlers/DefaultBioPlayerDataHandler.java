package john.mod.util.handlers;

import john.mod.BioElements;
import john.mod.util.interfaces.IBioPlayerDataHandler;

public class DefaultBioPlayerDataHandler implements IBioPlayerDataHandler
{
    private BioElements element;
    private Boolean maskActive = false;
    private int maskCharge = 10000;
    private final int maxCharge = 10000;

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

	@Override
    public Boolean getMaskActive()
	{
		return maskActive;
	}

	@Override
	public void setMaskActive(Boolean status)
	{
		this.maskActive = status;
	}

	@Override
	public int getMaskCharge()
	{
		return this.maskCharge;
	}

	@Override
	public void setMaskCharge(int charge)
	{
		this.maskCharge = charge;
	}

	@Override
	public boolean modifyMaskCharge(int modifier)
	{
		if (maskCharge + modifier >= 0)
		{
			if (maskCharge + modifier <= maxCharge)
			{
				maskCharge = maskCharge + modifier;
			}
			else
			{
				maskCharge = maxCharge;
			}
			return true;
		}
		else
		{
			maskCharge = 0;
			maskActive = false;
			return false;
		}
	}
}