package org.jboss.seam.remoting.messaging;

import java.io.Serializable;
import java.util.Set;

import org.jboss.cache.util.concurrent.ConcurrentHashSet;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("org.jboss.seam.remoting.messaging.SubscriptionRegistry.userTokens")
@Scope(ScopeType.SESSION)
public class UserTokens implements Serializable
{
   Set<String> tokens = new ConcurrentHashSet<String>();
   
   public void add(String token) {
      tokens.add(token);
   }
   
   public boolean contains(String token) {
      return tokens.contains(token); 
   }
   
   public void remove(String token) {
      tokens.remove(token);
   }
   
   @Destroy 
   public void cleanUp() {
       SubscriptionRegistry.instance().cleanupTokens(tokens);
   }
}
