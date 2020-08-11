module.exports = {
  preset: 'react-native',
  moduleFileExtensions: ['ts', 'tsx', 'js', 'jsx', 'json', 'node'],
  transform: { "^.+\\.js$": "<rootDir>/node_modules/react-native/jest/preprocessor.js" },
  testMatch: [
    '**/__tests__/**/*.test.(ts|js)',
  ],
  coverageThreshold: {
    global: {
      branches: 80,
      functions: 80,
      lines: 80,
      statements: 80
    }
  },
  coverageReporters: ['json', 'lcov', 'text', 'clover', 'cobertura'],
}