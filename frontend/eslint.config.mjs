import globals from "globals";
import pluginReact from "eslint-plugin-react";
import reactPlugin from "eslint-plugin-react";
import { defineConfig } from "eslint/config";

export default defineConfig([
  { files: ["**/*.{js,mjs,cjs,jsx}"], plugins: { react: reactPlugin}, extends: ["js/recommended"], languageOptions: { globals: globals.browser }, rules: { "react/prop-types": "off"}, settings: {
      react: {
        version: "detect",
      },
    }},
  pluginReact.configs.flat.recommended,
]);
