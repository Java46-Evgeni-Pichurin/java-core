package telran.people.predicates;

import telran.people.CompanyArray;
import telran.people.CompanySortedArray;

import java.util.function.Predicate;

public class CompanySortPredicate implements Predicate<CompanyArray> {

    @Override
    public boolean test(CompanyArray company) {
        return company instanceof CompanySortedArray;
    }
}
