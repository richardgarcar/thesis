'use strict';

var gulp = require('gulp');
var concat = require('gulp-concat');
var filter = require('gulp-filter');
var uglify = require('gulp-uglify');
var mainBowerFiles = require('main-bower-files');
var templateCache = require('gulp-angular-templatecache');
var plumber = require('gulp-plumber');
var livereload = require('gulp-livereload');

gulp.task('connect', function () {
    var connect = require('connect');
    var proxy = require('proxy-middleware');
    var serveStatic = require('serve-static');
    var url = require('url');
    var open = require('open');

    var app = connect();

    app.use('/src/api', proxy(url.parse('http://localhost:8080/cepv/api')));
    app.use(serveStatic('./'));
    app.listen(8081);

    open('http://localhost:8081/src')
});

gulp.task('application-js', function () {
    gulp.src('./src/app/**/*.js')
        .pipe(plumber())
        .pipe(concat('scripts.js'))
        .pipe(gulp.dest('./src'))
        .pipe(livereload())
});

gulp.task('templates', function () {
    gulp.src(['src/app/**/*.html'])
        .pipe(plumber())
        .pipe(templateCache({module: 'visualiserApp', root: 'app'}))
        .pipe(gulp.dest('src'))
        .pipe(livereload())
});

gulp.task('watch', ['connect'], function () {
    livereload.listen();
    gulp.watch('src/app/**/*.js', ['application-js']);
    gulp.watch('src/app/**/*.html', ['templates']);
});

gulp.task('build-application-js', ['application-js', 'templates'], function () {
    gulp.src(['./src/scripts.js', './src/templates.js'])
        .pipe(concat('scripts.js'))
        //.pipe(uglify())
        .pipe(gulp.dest('./build'));
});

gulp.task('third-party-js', function () {
    gulp.src(mainBowerFiles())
        .pipe(filter('**/*.js'))
        .pipe(concat('vendor.js'))
        .pipe(gulp.dest('./src'));
});

gulp.task('build-js', ['third-party-js', 'build-application-js'], function () {
    gulp.src('./src/vendor.js')
        //.pipe(uglify())
        .pipe(gulp.dest('./build'));
});

gulp.task('third-party-css', function () {
    gulp.src(mainBowerFiles())
        .pipe(filter('**/*.css'))
        .pipe(concat('vendor.css'))
        .pipe(gulp.dest('./src/css'));
});

gulp.task('third-party-other-resources', function () {
    gulp.src(mainBowerFiles())
        .pipe(filter('**/*.{ttf,woff,eot,svg}'))
        .pipe(gulp.dest('./src/css'));
});

gulp.task('main-css', ['third-party-css', 'third-party-other-resources'], function () {
    gulp.src(['./src/css/*.css'])
        .pipe(concat('styles.css'))
        .pipe(gulp.dest('./src/css'))
});

gulp.task('build-css', ['main-css'], function () {
    gulp.src(['./src/css/*'])
        .pipe(gulp.dest('./build/css'))
});

gulp.task('build-index', function () {
    gulp.src('./src/index.html')
        .pipe(gulp.dest('./build'));
});

gulp.task('build', ['build-js', 'build-css', 'build-index'], function () {
});

gulp.task('dev', ['build', 'watch'], function () {
});