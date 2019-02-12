package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose address contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindAddressCommand extends Command{

    public static final String COMMAND_WORD = "findAddress";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose addresses contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " John Street";

    private final Set<String> keywords;

    public FindAddressCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() { return new HashSet<>(keywords); }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithAddressContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose addresses contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithAddressContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInAddress = new HashSet<>(person.getAddress().getWordsInAddress());
            if (!Collections.disjoint(wordsInAddress, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }
}
