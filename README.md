# Household Appliances Warehouse

Command-line demo of a layered Java application that manages household appliance data stored in CSV files. The project highlights strict separation between DAO, service, controller, view, and startup/config layers without external frameworks.

## Architecture (what and why)
- **Entity layer**: domain models for appliances.
- **DAO layer**: CSV readers (`dao.impl.*`) behind `Dao` API and `DaoFactory`; sources are configurable via properties.
- **Service layer**: business operations through `ApplianceService` with `ServiceFactory`.
- **Controller layer**: `Controller`/`Response` API; `ConsoleController` parses commands and produces printable responses.
- **View layer**: `View` API with `ConsoleViewImpl` running the main interaction loop.
- **Startup layer**: `Config` API with `PropertiesConfigImpl` bootstrapping factories from `app.properties`; `Main` wires everything and handles unexpected crashes.

## Requirements
- JDK that matches `pom.xml` compiler level (currently set to 25; use a matching JDK or adjust the pom if needed).
- Maven 3.9+.

## Build and run
```bash
# compile (skips tests for speed)
mvn -q -DskipTests compile

# run with default config (src/main/resources/app.properties)
java -cp target/classes itpu.diyoramirzaeva.Main

# run with an alternate properties file on the classpath (without .properties suffix)
java -cp target/classes itpu.diyoramirzaeva.Main custom_config
```

## Usage (CLI commands)
- `help` – show available commands.
- `list [all|air|fridge|iron]` – print inventory.
- `search air heating=true noise=38.5`
- `search fridge hasFreezer=true energyClass=A++`
- `search iron capacity=0.35`
- `exit` – quit the application.

Client-side validation in the view layer checks empty input, unknown commands, categories, and malformed `key=value` parameters before hitting the controller.

## Configuration
`src/main/resources/app.properties` controls component choices and CSV names:
```
view = console        # console | batch
controller = console
service = simple
dao = csv
source = csv
source.air = air_conditioners
source.fridge = fridges
source.iron = irons
# batch-mode file (classpath or filesystem path) when view=batch
batch.file = batch-commands.txt
```
Each `source.*` value points to a CSV file on the classpath (suffix `.csv` is added automatically).

### Batch mode
Set `view = batch` and place commands (one per line) in the file given by `batch.file`. Example `batch-commands.txt` is provided under `src/main/resources`.

## Testing
Run all unit tests:
```bash
mvn test
```
