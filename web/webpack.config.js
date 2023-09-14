const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    createPlaylist: path.resolve(__dirname, 'src', 'pages', 'createPlaylist.js'),
    viewPlaylist: path.resolve(__dirname, 'src', 'pages', 'viewPlaylist.js'),
    searchPlaylists: path.resolve(__dirname, 'src', 'pages', 'searchPlaylists.js'),
    test: path.resolve(__dirname, 'src', 'pages', 'test.js'),
    digiPantryCreatePantry: path.resolve(__dirname, 'src', 'pages', 'digiPantryCreatePantry.js'),
    digiPantryHome: path.resolve(__dirname, 'src', 'pages', 'digiPantryHome.js'),
    digiPantryInventory: path.resolve(__dirname, 'src', 'pages', 'digiPantryInventory.js'),
    digiPantryViewPantries: path.resolve(__dirname, 'src', 'pages', 'digiPantryViewPantries.js'),
    digiPantryRecipes: path.resolve(__dirname, 'src', 'pages', 'digiPantryRecipes.js'),
    digiPantryMealPlan: path.resolve(__dirname, 'src', 'pages', 'digiPantryMealPlan.js'),
    digiPantryRecipeViewer: path.resolve(__dirname, 'src', 'pages', 'digiPantryRecipeViewer.js'),

  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
