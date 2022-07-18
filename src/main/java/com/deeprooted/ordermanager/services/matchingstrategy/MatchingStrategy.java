package com.deeprooted.ordermanager.services.matchingstrategy;

import com.deeprooted.ordermanager.models.Ledger;
import com.deeprooted.ordermanager.models.Transaction;

import java.util.ArrayList;

/*
    Each MatchingStrategy need to follow this Interface
    and override computeMatching to write its own computing Algorithm
 */
public interface MatchingStrategy {
    public ArrayList<Transaction> computeMatching(Ledger ledger);
}
