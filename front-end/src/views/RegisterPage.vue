<template>
<div class="container">
  <div class="row justify-content-center">
    <div class="register-form">
      <div class="logo-wrapper">
        <img class="logo" src="/static/images/logo.png">
        <div class="tagline">Open source task management tool</div>
      </div>
      <form @submit="submitForm">
        <div v-show="errorMessage" class="alert alert-danger failed">{{errorMessage}}</div>
        <div class="form-group">
          <label for="username">Username</label>
          <input type="text" class="form-control" id="username" v-model="form.username">
        </div>
        <div class="form-group">
          <label for="emailAddress">Email address</label>
          <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" v-model="form.password">
        </div>
        <button type="submit" class="btn btn-primary btn-block">
          Create Account
        </button>
      </form>
    </div>
  </div>
  <footer class="footer">
    <span class="copyright"> ... </span>
    <ul class="footer-links list-inline float-right">...</ul>
  </footer>
</div>
</template>

<script>
export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      }
    }
  },
  methods: {
    submitForm () {

    }
  }
}
</script>

<style lang="scss" scoped>
  .container{max-width: 900px;}
  .register-form{margin-top: 50px; max-width: 325px;}
  .logo-wrapper{margin-bottom: 35px}
  .footer{width: 100%; line-height: 40px; margin-top: 50px;}

</style>
