package br.ufsc.ine5605.ShardRPG.Item;

public class ItemPainting  extends Item implements Inspectable, Visible{
	
	private String inspectMessage;
	private boolean isVisible;
	private boolean playerRecogniseItself;
	
	public ItemPainting(String description, String name, String[] alias) {
		super(description, name, alias);
		this.inspectMessage = "This is an antique painting, there seems to be a family on it. They're in what appears to be a luxurious resting chamber.";
	}

	public void inspect() {
		if(this.inspectMessage!= null) {
			if(playerRecogniseItself) {
				System.out.println(this.inspectMessage);
			} else {
				System.out.println(this.inspectMessage + "\n Wait, that's you!");
			}
		}else {
			System.out.println("This is a "+ this +".");
		}
		
	}

	public void setInspectMessage(String string) {
		this.inspectMessage = string;
	}

	public boolean isVisible() {
		return this.isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean playerRecogniseItself() {
		return playerRecogniseItself;
	}

	public void setplayerRecogniseItself(boolean doesPlayerReconigseItself) {
		this.playerRecogniseItself = doesPlayerReconigseItself;
	}
	

}
