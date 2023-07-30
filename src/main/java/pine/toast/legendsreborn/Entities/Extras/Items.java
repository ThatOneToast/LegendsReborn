package pine.toast.legendsreborn.Entities.Extras;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Items implements Listener {
  
  @EventHandler
  public void onItemBurnOrExplode(EntityDamageEvent event) {
    
    if(!(event.getEntity() instanceof Item item)) return;
    
    event.setCancelled(true);
    
  }
  
  
}