# Contributing to SDKForge

Thank you for your interest in contributing to SDKForge!
This document provides guidelines and information for contributors.

## Table of Contents

- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Project Structure](#project-structure)
- [Code Style](#code-style)
- [Testing](#testing)
- [Pull Request Process](#pull-request-process)
- [Reporting Issues](#reporting-issues)
- [Code of Conduct](#code-of-conduct)

## Getting Started

Before contributing, please:

1. Fork the repository
2. Create a branch from `main` (see [Branch Naming](#branch-naming))
3. Set up your development environment
4. Make your changes
5. Test your changes
6. Submit a pull request (see [Pull Request Guidelines](#pull-request-guidelines))

## Development Setup

### Prerequisites

- **Java Development Kit (JDK)**: Version 21 or higher
- **Android Studio**: Latest stable version (for Android development)
- **Xcode**: Latest stable version (for iOS development, macOS only)
- **Kotlin**: Version 1.9.0 or higher
- **Gradle**: Version 8.0 or higher

### Environment Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/SDKForge/template-sdk.git SDKForgeTemplate
   or
   git clone git@github.com:SDKForge/template-sdk.git SDKForgeTemplate

   cd SDKForgeTemplate
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run tests**:
   ```bash
   ./gradlew lint ktlintCheck dependencyGuard checkLegacyAbi
   ```

## Project Structure

This is a Kotlin Multiplatform project with the following structure:

```
SDKForge.Template/
├── app-android/         # Android sample application
├── app-ios/             # iOS sample application
├── app-shared/          # Shared sample UI components (Compose Multiplatform)
├── shared/              # Library with all components
├── shared-core/         # Core shared functionality
├── shared-template/     # Template for shared modules
├── build-logic/         # Gradle build logic
├── internal-benchmark/  # Performance benchmarks
└── internal-ktlint/     # Custom ktlint rules
```

### Module Guidelines

- **Shared modules** (`shared*`): Should be platform-agnostic and reusable
- **App modules** (`app*`): Platform-specific implementations
- **Internal modules** (`internal*`): Build tools and utilities

## Branch and Commit Conventions

### Branch Naming

Use the following format for branch names:

```
[branch type]/[ticket id]-[ticket name]
```

**Branch Types:**

- `feature/` - New features
- `fix/` - Bug fixes
- `docs/` - Documentation changes
- `refactor/` - Code refactoring
- `test/` - Adding or updating tests
- `chore/` - Maintenance tasks

**Examples:**

```
feature/7-ktlint-rules-for-imports
fix/12-crash-on-android-12
docs/5-update-api-documentation
refactor/8-improve-error-handling
```

### Commit Message Format

Use the following format for commit messages:

```
#[ticket id]: ticket name
- changes purpose #1
- changes purpose #2
```

**Examples:**

```
#7: ktlint rules for proper structure model imports
- fixed ktlint issues
- added unit tests

#12: crash on Android 12
- fixed platform-specific code
- updated minimum SDK version
- added regression tests
```

### Pull Request Title Format

Use the same format as commit messages for PR titles:

```
#[ticket id]: ticket name
```

**Example:**

```
#7: ktlint rules for proper structure model imports
```

## Code Style

### Kotlin Style Guide

We follow the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html) and
use ktlint for code formatting.

### Code Formatting

1. **Automatic formatting**:
   ```bash
   ./gradlew ktlintFormat
   ```

2. **Check formatting**:
   ```bash
   ./gradlew ktlintCheck
   ```

### Naming Conventions

- **Files**: Use PascalCase for class names, camelCase for functions and variables
- **Packages**: Use lowercase with dots (e.g., `com.sdkforge.template`)
- **Constants**: Use UPPER_SNAKE_CASE
- **Functions**: Use camelCase, descriptive names

### Documentation

- Use KDoc for public APIs
- Include examples for complex functions
- Document platform-specific behavior
- Keep documentation up-to-date with code changes

## Testing

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :shared-core:test

# Run tests with coverage
./gradlew koverReport
```

### Test Guidelines

- Write unit tests for all public APIs
- Use descriptive test names
- Follow AAA pattern (Arrange, Act, Assert)
- Mock external dependencies
- Test both success and failure scenarios

## Pull Request Process

### Before Submitting

1. **Ensure code quality**:
    - All tests pass
    - Code is formatted (ktlint passes)
    - No linting errors
    - Documentation is updated

2. **Check compatibility**:
    - Works on all target platforms
    - No breaking changes (unless documented)
    - Backward compatibility maintained

3. **Update documentation**:
    - README.md if needed
    - API documentation
    - Migration guides for breaking changes

### Pull Request Guidelines

1. **Title**: Use the ticket-based format
   ```
   #[ticket id]: ticket name
   ```

   **Example:**
   ```
   #7: ktlint rules for proper structure model imports
   ```

2. **Description**: Include:
    - What changes were made
    - Why changes were made
    - How to test the changes
    - Any breaking changes
    - List of changes (using the same format as commit messages):
      ```
      - change purpose #1
      - change purpose #2
      ```

3. **Labels**: Add appropriate labels:
    - `enhancement` for new features
    - `bug` for bug fixes
    - `documentation` for docs
    - `breaking-change` for breaking changes

### Review Process

1. **Automated checks** must pass:
    - Build verification
    - Test execution
    - Code quality checks
    - Dependency updates

2. **Code review**:
    - At least one maintainer approval required
    - Address all review comments
    - Resolve conflicts if any

## Reporting Issues

### Bug Reports

When reporting bugs, please include:

1. **Environment details**:
    - Operating system
    - Kotlin version
    - Gradle version
    - Target platform (Android/iOS)

2. **Steps to reproduce**:
    - Clear, step-by-step instructions
    - Sample code if applicable
    - Expected vs actual behavior

3. **Additional information**:
    - Stack traces
    - Logs
    - Screenshots (for UI issues)

### Feature Requests

For feature requests:

1. **Describe the feature**:
    - What it should do
    - Why it's needed
    - Use cases

2. **Consider implementation**:
    - Platform compatibility
    - Performance impact
    - Backward compatibility

## Code of Conduct

### Our Standards

We are committed to providing a welcoming and inspiring community for all. Please:

- Be respectful and inclusive
- Use welcoming and inclusive language
- Be collaborative and constructive
- Focus on what is best for the community
- Show empathy towards other community members

### Enforcement

Instances of abusive, harassing, or otherwise unacceptable behavior may be reported by contacting
the project team. All complaints will be reviewed and investigated promptly and fairly.

## License

By contributing to SDKForge, you agree that your contributions will be licensed under the
MIT License.

## Getting Help

If you need help with contributing:

1. **Check existing issues** and pull requests
2. **Read the documentation** in the `docs/` folder
3. **Ask questions** in GitHub Discussions
4. **Join our community** channels (if available)

Thank you for contributing to SDKForge!
