# Entity-Relationship Diagram (ERD) - v1

This document outlines the database schema for the GuidesTürkiye application.

---

## Tables

### `cities`
Stores the cities where locations are found.

| Field          | Type          | Constraints/Notes                               |
| :------------- | :------------ | :---------------------------------------------- |
| **id** | `BIGSERIAL`   | Primary Key                                     |
| **name** | `VARCHAR(120)`| Not Null                                        |
| **country_code**| `CHAR(2)`     | Nullable                                        |

* **Indexes:** `(name)`

---

### `categories`
Stores the categories used to tag locations (e.g., "Historic", "Restaurant").

| Field  | Type           | Constraints/Notes           |
| :----- | :------------- | :-------------------------- |
| **id** | `BIGSERIAL`    | Primary Key                 |
| **name**| `VARCHAR(80)`  | Not Null, Unique            |
| **slug**| `VARCHAR(100)` | Not Null, Unique, URL-safe  |

---

### `locations`
The core table containing information about each travel location.

| Field       | Type           | Constraints/Notes                         |
| :---------- | :------------- | :---------------------------------------- |
| **id** | `BIGSERIAL`    | Primary Key                               |
| **name** | `VARCHAR(160)` | Not Null                                  |
| **description**| `TEXT`         | Nullable                                  |
| **latitude** | `DECIMAL(9,6)` | Not Null                                  |
| **longitude** | `DECIMAL(9,6)` | Not Null                                  |
| **city_id** | `BIGINT`       | Not Null, Foreign Key -> `cities(id)`     |
| **avg_rating**| `NUMERIC(2,1)` | Nullable (can be computed later)          |

* **Indexes:** `(name)`, `(city_id)`, `(latitude, longitude)`

---

### `location_categories` (Join Table)
Links locations to their categories in a many-to-many relationship.

| Field         | Type     | Constraints/Notes                           |
| :------------ | :------- | :------------------------------------------ |
| **location_id** | `BIGINT` | Not Null, Foreign Key -> `locations(id)`    |
| **category_id** | `BIGINT` | Not Null, Foreign Key -> `categories(id)`   |

* **Primary Key:** Composite key on `(location_id, category_id)`

---

### `images`
Stores URLs for images associated with a location.

| Field       | Type           | Constraints/Notes                         |
| :---------- | :------------- | :---------------------------------------- |
| **id** | `BIGSERIAL`    | Primary Key                               |
| **location_id**| `BIGINT`       | Not Null, Foreign Key -> `locations(id)`  |
| **url** | `TEXT`         | Not Null                                  |
| **alt** | `VARCHAR(160)` | Nullable (Alternative text for the image) |

* **Indexes:** `(location_id)`

---

## Future Tables
The following tables are planned but are not part of the v1 schema:
* `users`
* `reviews`
* `favorites`

---

## Business Rules & Policies

1.  **Data Seeding:** When seeding the database, `city` and `category` names should be treated as case-insensitive to prevent duplicates (e.g., "Mersin" and "mersin" are the same).
2.  **Coordinates:** `(latitude, longitude)` pairs are considered valid Earth coordinates. They are not required to be globally unique.
3.  **Deletion Policy:** A `CASCADE` delete policy should be implemented. Deleting a `location` will automatically delete its associated `images` and `location_categories` entries.
4.  **Slugs:** Category slugs must be lowercase and URL-safe (e.g., no spaces or special characters).