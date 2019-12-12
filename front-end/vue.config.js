module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8080'
      }
    },
    configureWebpack: {
      entry: {
        app: './src/main.js',
        style: [
          'bootstrap/dis/css/bootstrap.min.css'
        ]
      }
    }
  }
}
module.exports = {
  devServer: {
    clientLogLevel: 'info'
  }
};
