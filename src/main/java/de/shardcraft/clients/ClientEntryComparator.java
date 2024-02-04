package de.shardcraft.clients;

import java.util.Comparator;

public class ClientEntryComparator implements Comparator<ClientEntry> {
  @Override
  public int compare(ClientEntry o1, ClientEntry o2) {
    if (o1 != null && o1.equals(ClientEntry.newClient)) {
      return 1;
    }
    if (o2 != null && o2.equals(ClientEntry.newClient)) {
      return -1;
    }
    return Comparator.comparing(ClientEntry::getClientId).compare(o1, o2);
  }
}
