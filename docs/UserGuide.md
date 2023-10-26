---
layout: page
title: User Guide
---

MediLink Contacts(MLC) is a **desktop app for managing patients and doctors details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MLC can get your patients management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `mediLink.jar` from [here](https://github.com/AY2324S1-CS2103T-T09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MLC.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar medilink.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add-doctor n/John Doe ic/S9851386G g/M p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a doctor named `John Doe` to the Address Book.

   * `delete ic/S9851386G` : Deletes the person with ic S9851386G.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a Doctor: `add-doctor`

Adds a Doctor to the hospital database.

Format: `add-doctor n/NAME ic/IC g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:**
A person can have any number of tags (including 0)
</div>
<div markdown="block" class="alert alert-info">

**:information_source: Take Note:**<br>

- A doctor **MUST** have a non-empty NAME and a valid IC at the very least. 
Failure to include these details may result in an error.
- Phone Numbers and Emails have to be in a valid format.
- PHONE_NUMBER must have exactly 8 digits.
- EMAIL must contain email domain (eg. `@gmail.com`).
- PATIENT must contain the valid IC of a Patient in the Database.
</div>

Examples:
* `add-doctor n/John Doe ic/S9851386G g/M p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pt/T0123456H`
* `add-doctor n/Betsy Crowe ic/S9851586G g/F p/98765433 e/betsycrowe@example.com a/#104-C, Wakanda St 42 t/Surgeon`


### Adding a Patient: `add-patient`

Adds a Patient to the hospital database.

Format: `add-patient n/NAME ic/IC g/GENDER p/PHONE_NUMBER ec/EMERGENCY_CONTACT e/EMAIL a/ADDRESS [t/TAG] [d/DOCTOR] [c/CONDITION] [b/BLOODTYPE] …​`

<div markdown="block" class="alert alert-info">

**:information_source: Take Note:**<br>

- A patient **MUST** have a non-empty NAME and a valid IC at the very least. Failure to include these details may result in an error.
- Phone Numbers and Emails have to be in a valid format.
    - PHONE_NUMBER must have exactly 8 digits.
    - EMAIL must contain email domain (eg. `@gmail.com`).
- DOCTOR must contain the valid IC of a doctor in the Database.
- EMERGENCY_CONTACT must contain valid emergency contact number, which needs to be a valid phone number.
- Blood type must be a combination of A/B/AB/O and +/-
</div>

Examples:
* `add-patient n/John Doe ic/S9851386G g/M p/98765432 ec/90123456 e/johnd@example.com a/John street, block 123, #01-01 d/T0123456H c/pneumothorax b/O+`
* `add-patient n/Betsy Crowe ic/S9851586G g/F p/98765433 e/betsycrowe@example.com a/#104-C, Wakanda St 42 t/High Priority pt/T0123556H`

### Creating an Appointment : `new-appt`

Creates a new appointment for patients.

Format: `new-appt pic/IC dic/IC time/yyyy-MM-dd HH:mm:ss`

<div markdown="block" class="alert alert-info">
**:Note: Take Note:**<br>

- All fields are Required.
- EMAIL must follow the specified format (ie. `yyyy-MM-dd HH:mm:ss`).
- PATIENT must contain the valid IC of a Patient in the Database.
- DOCTOR must contain the valid IC of a Doctor in the Database.
</div>

Examples:
* `new-appt pic/T0123456H dic/S9851586G time/yyyy-MM-dd 13:00:00`

### Listing all persons : `list`

Shows a list of all persons in the MediLink Contacts.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the MediLink Contacts.

Format: `edit NRIC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `NRIC`. The NRIC **must be a valid IC number**
* At least one of the optional fields must be provided.
* Must edit appropriate fields based on whether the person is a patient or doctor (e.g. can't update condition of a doctor)
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit T0123456A p/91234567 e/johndoe@example.com g/F` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit S9876543B pt/T0123456A n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons that match the query. Supports gender, NRIC and name.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* When searching by NRIC, input the NRIC as the keyword.
* When searching by gender, input either `M` or `F` as the keyword.
* When searching by name, input the names as per above. 

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find T1125726G` returns the person with the matching NRIC.
* `find M` returns all male persons.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete NRIC`

* Deletes the person with the specified NRIC.
* The NRIC **must be a valid NRIC format** and must belong to a person in the addressbook.
* The NRIC is case-sensitive. e.g `tXXXXXXXz` is not the same as `TXXXXXXXZ`

Examples:
* `delete S1234567J` deletes Jonathan who has the NRIC `S1234567J`

### Clearing all entries : `clear`

Clears all entries from the MediLink Contacts.

Format: `clear`

### Undo last action : `undo`

Undoes the effect of the last command.

Format: `undo`

* Can only do upto 5 undos at any one time.

### Redo last action : `redo`

Repeats the previous command; an `undo` for an `undo` command.

Format: `redo`

* Can only do upto 5 redos at any one time.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MediLink Contacts data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediLink Contacts data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MediLink Contacts will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediLink Contacts home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                                                                                                                                                                                                             |
|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **New Doctor**      | `add-doctor n/NAME ic/IC g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add-doctor n/John Doe ic/S9851386G g/M p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pt/T0123456H`                                                                                               |
| **New Patient**     | `add-patient n/NAME ic/IC g/GENDER p/PHONE_NUMBER ec/EMERGENCY_CONTACT e/EMAIL a/ADDRESS [t/TAG] [d/DOCTOR] [c/CONDITION] [b/BLOODTYPE] …​` <br> e.g., `add-patient n/John Doe ic/S9851386G g/M p/98765432 ec/90123456 e/johnd@example.com a/John street, block 123, #01-01 d/T0123456H c/pneumothorax b/O+` |
| **New Appointment** | `new-appt pic/IC dic/IC time/yyyy-MM-dd HH:mm:ss` <br> e.g., `new-appt pic/T0123456H dic/S9851586G time/yyyy-MM-dd 13:00:00`                                                                                                                                                                                 |
| **Clear**           | `clear`                                                                                                                                                                                                                                                                                                      |
| **Undo**            | `undo`                                                                                                                                                                                                                                                                                                       |
| **Redo**            | `redo`                                                                                                                                                                                                                                                                                                       |
| **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                          |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                                                                                                  |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                   |
| **List**            | `list`                                                                                                                                                                                                                                                                                                       |
| **Help**            | `help`                                                                                                                                                                                                                                                                                                       |

