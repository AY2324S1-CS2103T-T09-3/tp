package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Remark;

/**
 * The DeleteCommandParser class is responsible for parsing user input and creating a DeleteCommand object
 * to represent the user's request to delete a record. It interprets the provided input arguments and converts them
 * into a valid DeleteCommand.
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @param args The user input string containing the details for deleting a record.
     * @return A DeleteCommand object representing the user's request to delete a record.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Ic nric;
        try {
            nric = ParserUtil.parseIc(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remarkString = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(remarkString);

        return new RemarkCommand(nric, remark);
    }
}
