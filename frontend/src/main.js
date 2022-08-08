import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'






// CKEditor importing
import CKEditor from '@ckeditor/ckeditor5-vue';
        // CKEditor Translation
        import '@ckeditor/ckeditor5-build-classic/build/translations/ko';


// Bootstrap importing
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap"

// import custom.scss for sass
import './sass/custom.sass'

// FontAwesome importing
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUserSecret, faLock, faLockOpen, faCircleUser, faBorderAll, faMagnifyingGlass, faHeart, faCircleQuestion, faCommentDots, faStar, faTruck, faGear, faBars, faPhoneFlip, faTicket, faCircleCheck, faX, faHome, faFloppyDisk, faClockRotateLeft, faBell, faEnvelope} from '@fortawesome/free-solid-svg-icons'
library.add(faUserSecret,faLock, faLockOpen, faCircleUser, faBorderAll, faMagnifyingGlass, faHeart,faCircleQuestion, faCommentDots, faStar, faTruck, faGear, faBars, faPhoneFlip, faTicket, faCircleCheck, faX, faHome, faFloppyDisk, faClockRotateLeft, faBell, faEnvelope)


createApp(App).use(store).use(router).component('font-awesome-icon', FontAwesomeIcon).use(CKEditor).mount('#app')
