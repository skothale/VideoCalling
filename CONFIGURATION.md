# Backend Configuration

The backend uses environment variables for all configuration. No hardcoded values are used.

## Server Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `SERVER_PORT` | `8080` | Port for the server to listen on |
| `SERVER_ADDRESS` | `0.0.0.0` | Address for the server to bind to |

## Database Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `DATABASE_URL` | `jdbc:h2:mem:testdb` | Database connection URL |
| `DATABASE_DRIVER` | `org.h2.Driver` | Database driver class |
| `DATABASE_USERNAME` | `sa` | Database username |
| `DATABASE_PASSWORD` | `password` | Database password |
| `H2_CONSOLE_ENABLED` | `true` | Enable H2 console |
| `H2_CONSOLE_PATH` | `/h2-console` | H2 console path |

## JPA Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `JPA_DIALECT` | `org.hibernate.dialect.H2Dialect` | JPA dialect |
| `JPA_DDL_AUTO` | `create-drop` | DDL auto mode |
| `JPA_SHOW_SQL` | `true` | Show SQL queries |
| `JPA_FORMAT_SQL` | `true` | Format SQL queries |

## CORS Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000,http://localhost:3001,http://127.0.0.1:3000,http://127.0.0.1:3001` | Allowed CORS origins (comma-separated) |
| `CORS_ALLOWED_METHODS` | `GET,POST,PUT,DELETE,OPTIONS` | Allowed HTTP methods |
| `CORS_ALLOWED_HEADERS` | `*` | Allowed headers |
| `CORS_ALLOW_CREDENTIALS` | `true` | Allow credentials |

**Important**: When `CORS_ALLOW_CREDENTIALS` is `true`, you cannot use `*` for `CORS_ALLOWED_ORIGINS`. You must specify explicit origins.

### CORS Examples:

**Development:**
```bash
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:3001,http://127.0.0.1:3000
CORS_ALLOW_CREDENTIALS=true
```

**Production:**
```bash
CORS_ALLOWED_ORIGINS=https://yourdomain.com,https://www.yourdomain.com
CORS_ALLOW_CREDENTIALS=true
```

**Network Development:**
```bash
CORS_ALLOWED_ORIGINS=http://192.168.1.7:3000,http://192.168.1.7:3001
CORS_ALLOW_CREDENTIALS=true
```

**If you need to allow all origins (not recommended with credentials):**
```bash
CORS_ALLOWED_ORIGINS=*
CORS_ALLOW_CREDENTIALS=false
```

## WebSocket Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `WEBSOCKET_ALLOWED_ORIGINS` | `*` | Allowed WebSocket origins |

## Performance Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `DB_MAX_POOL_SIZE` | `20` | Database connection pool max size |
| `DB_MIN_IDLE` | `5` | Database connection pool min idle |
| `DB_CONNECTION_TIMEOUT` | `30000` | Database connection timeout (ms) |
| `WS_MAX_TEXT_SIZE` | `8192` | WebSocket max text message size |
| `WS_MAX_BINARY_SIZE` | `8192` | WebSocket max binary message size |
| `TOMCAT_MAX_THREADS` | `200` | Tomcat max threads |
| `TOMCAT_MIN_THREADS` | `10` | Tomcat min threads |
| `TOMCAT_MAX_CONNECTIONS` | `8192` | Tomcat max connections |

## Application Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `MEETING_CLEANUP_INTERVAL` | `300000` | Meeting cleanup interval (ms) |
| `MAX_PARTICIPANTS` | `50` | Maximum participants per meeting |
| `MAX_MEETINGS` | `100` | Maximum concurrent meetings |
| `MEETING_IDLE_TIMEOUT` | `1800000` | Meeting idle timeout (ms) |

## Security Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `JWT_SECRET` | `your-secret-key-change-in-production` | JWT secret key |
| `JWT_EXPIRATION` | `86400000` | JWT expiration time (ms) |

## Feature Flags

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `ENABLE_EDUCATIONAL_MODE` | `true` | Enable educational mode |
| `ENABLE_SCREEN_SHARING` | `true` | Enable screen sharing |
| `ENABLE_CHAT` | `true` | Enable chat functionality |
| `ENABLE_DEBUG_MODE` | `false` | Enable debug mode |

## Logging Configuration

| Environment Variable | Default | Description |
|---------------------|---------|-------------|
| `LOG_LEVEL` | `INFO` | Application log level |
| `SPRING_LOG_LEVEL` | `INFO` | Spring framework log level |
| `SECURITY_LOG_LEVEL` | `INFO` | Security log level |

## Example Environment Files

### Development (.env.local)
```bash
SERVER_PORT=8080
DATABASE_URL=jdbc:h2:mem:testdb
JWT_SECRET=dev-secret-key
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:3001,http://127.0.0.1:3000
ENABLE_DEBUG_MODE=true
LOG_LEVEL=DEBUG
```

### Production (.env.production)
```bash
SERVER_PORT=443
DATABASE_URL=jdbc:postgresql://db:5432/videocalling
JWT_SECRET=your-super-secure-production-secret-key
CORS_ALLOWED_ORIGINS=https://yourdomain.com,https://www.yourdomain.com
ENABLE_DEBUG_MODE=false
LOG_LEVEL=WARN
```

### Docker (.env.docker)
```bash
SERVER_PORT=8080
DATABASE_URL=jdbc:postgresql://postgres:5432/videocalling
JWT_SECRET=docker-secret-key
CORS_ALLOWED_ORIGINS=http://frontend:3000
ENABLE_DEBUG_MODE=false
```

## Running with Environment Variables

```bash
# Set environment variables and run
export SERVER_PORT=9090
export JWT_SECRET=my-secret-key
mvn spring-boot:run

# Or use a .env file
source .env.local
mvn spring-boot:run

# Or pass directly to the command
SERVER_PORT=9090 JWT_SECRET=my-secret-key mvn spring-boot:run
``` 