import { mount, createLocalVue } from '@vue/test-utils'
import VueRouter from 'vue-router'
import Vuelidate from 'Vuelidate'
import registrationService from '../../src/services/registration'
import RegisterPage from '../../src/views/RegisterPage'

// adding vue-router to the test to access vm.$touter
const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.use(Vuelidate)
const router = new VueRouter()

// mock dependency registrationService
jest.mock('../../src/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  let registerSpy

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
    // Create spy
    registerSpy = jest.spyOn(registrationService, 'register')
  })

  afterEach(() => {
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  it('should render registration form', () => {
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text())
      .toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create Account')
  })

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs bound with data model', () => {
    const username = 'sunny'
    const emailAddress = 'sunny@local'
    const password = 'VueJsRocks!'

    wrapper.vm.form.username = username
    wrapper.vm.form.emailAddress = emailAddress
    wrapper.vm.form.password = password
    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldEmailAddress.element.value).toEqual(emailAddress)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('should have form submit event handler `submit form`', () => {
    const stub = jest.fn()
    wrapper.setMethods({ submitForm: stub })
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should register when it is a new user', async () => {
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.emailAddress = 'sunny@local.com'
    wrapper.vm.form.password = 'JestRocks!'
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
  })

  it('should fail if it is not a new user', async () => {
    expect.assertions(3)
    // in the mock only sunny@local.com is new user
    wrapper.vm.form.username = 'CorrectUsername'
    wrapper.vm.form.emailAddress = 'makus@local.com'
    wrapper.vm.form.password = 'CorrectPassword123!'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  it('should fail when the email address is invalid', () => {
    wrapper.vm.form.username = 'CorrectUsername'
    wrapper.vm.form.emailAddress = 'bad-email-address'
    wrapper.vm.form.password = 'CorrectPassword123!'
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })

  it('should fail if username is invalid', () => {
    wrapper.vm.form.username = 't'
    wrapper.vm.form.emailAddress = 'correct@mail.com'
    wrapper.vm.form.password = 'CorrectPassword123!'
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })

  it('should fail if password is invalid', () => {
    wrapper.vm.form.username = 'CorrectUsername'
    wrapper.vm.form.emailAddress = 'correct@username.com'
    wrapper.vm.form.password = 't'
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })
})

// describe('RegisterPage.vue', () =>{
//   it('should render correct contents', () =>{
//     const Constructor = Vue.extend(RegisterPage)
//     const vm = new Constructor().$mount()
//     expect(vm.$el.querySelector('.logo').getAttribute('src'))
//       .toEqual('/static/images/logo.png')
//     expect(vm.$el.querySelector('.tagline').textContent)
//       .toEqual('Open source task management tool')
//     // expect(vm.$el.querySelector('#username').value).toEqual('')
//     // expect(vm.$el.querySelector('#emailAddress').value).toEqual('')
//     // expect(vm.$el.querySelector('#password').value).toEqual('')
//     // expect(vm.$el.querySelector('form' +
//     //   'button[type="submit"]').textContent).toEqual('Create amount')
//   })
// })
