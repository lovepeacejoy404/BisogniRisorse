import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;


class CheckListItem
{
	// Represents items in the list that can be selected

	
   private String  label;
   private boolean isSelected = false;

   public CheckListItem(String label)
   {
      this.label = label;
   }

   public boolean isSelected()
   {
      return isSelected;
   }

   public void setSelected(boolean isSelected)
   {
      this.isSelected = isSelected;
   }

   public String toString()
   {
      return label;
   }
   
// Handles rendering cells in the list using a check box

		static MouseAdapter ma = new MouseAdapter()
	    {
	        public void mouseClicked(MouseEvent event)
	        {
	           JList list = (JList) event.getSource();
	           
	           // Get index of item clicked
	           
	           int index = list.locationToIndex(event.getPoint());
	           CheckListItem item = (CheckListItem)
	              list.getModel().getElementAt(index);
	           
	           // Toggle selected state
	           
	           item.setSelected(! item.isSelected());
	           
	           // Repaint cell
	           
	           list.repaint(list.getCellBounds(index, index));
	        }
	     };
}