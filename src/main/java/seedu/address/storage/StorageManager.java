package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private AddressBookStorage prevAddressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, AddressBookStorage prevAddressBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.prevAddressBookStorage = prevAddressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        //First store current state in prevAddressBook
        backupAddressBook(addressBook);
        //then save new state into currentAddressBook
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public void backupAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        try {
            saveAddressBookToPrevStorage(addressBook);
        } catch (DataLoadingException e) {
            throw new IOException("Couldn't read data");
        }
    }

    private void saveAddressBookToPrevStorage(ReadOnlyAddressBook addressBook) throws IOException {
        logger.fine("Backing up AddressBook to prev storage");
        prevAddressBookStorage.saveAddressBook(addressBook, prevAddressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void undoAddressBook() throws IOException {
        try {
            ReadOnlyAddressBook previousData = readPrevAddressBook().orElseThrow();
            saveAddressBook(previousData);
        } catch (DataLoadingException e) {
            throw new IOException("Couldn't load previous data");
        }
    }

    private Optional<ReadOnlyAddressBook> readPrevAddressBook() throws DataLoadingException {
        logger.fine("Reading from previous AddressBook storage");
        return prevAddressBookStorage.readAddressBook(prevAddressBookStorage.getAddressBookFilePath());
    }

}
