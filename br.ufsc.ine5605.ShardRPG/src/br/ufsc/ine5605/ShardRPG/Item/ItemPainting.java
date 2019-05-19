package br.ufsc.ine5605.ShardRPG.Item;

public class ItemPainting extends Item implements Inspectable, Visible {

	private String inspectMessage;

	private boolean isVisible;

	private boolean playerRecogniseItself;


	public ItemPainting(String description, String name, String[] alias) {
		super(description, name, alias, false);
		inspectMessage = "This is an antique painting, there seems to be a family on it. They're in what appears to be a luxurious resting chamber.";
	}


	@Override
	public void inspect() {
		if (inspectMessage != null) {
			if (playerRecogniseItself) {
				System.out.println(inspectMessage);
			} else {
				System.out.println(inspectMessage + "\n Wait, that's you!");
			}
		} else {
			System.out.println("This is a " + this + ".");
		}

	}


	public String getInspectMessage() {
		return inspectMessage;
	}


	@Override
	public void setInspectMessage(String string) {
		inspectMessage = string;
	}


	@Override
	public boolean isVisible() {
		return isVisible;
	}


	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public boolean playerRecogniseItself() {
		return playerRecogniseItself;
	}


	public void setplayerRecogniseItself(boolean doesPlayerReconigseItself) {
		playerRecogniseItself = doesPlayerReconigseItself;
	}

}
