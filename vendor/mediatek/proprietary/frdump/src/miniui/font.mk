
ifeq ($(LCM_WIDTH),)
  $(error LCM_WIDTH was not defined!!)
endif

$(warning LCM_WIDTH=$(LCM_WIDTH), LCM_HEIGHT=$(LCM_HEIGHT))

ifeq ($(MTK_FACTORY_MODE_IN_GB2312),yes)
#########LOCAL_CFLAGS += -DSUPPORT_GB2312	

$(warning SUPPORT_GB2312=yes)

ifeq ($(LCM_WIDTH),1440)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_48x48
endif
ifeq ($(LCM_WIDTH),1400)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_48x48
endif
ifeq ($(LCM_WIDTH),1280)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),1152)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),1080)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),1050)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),1024)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),960)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),864)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),800)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),720)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),600)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_32x32
endif
ifeq ($(LCM_WIDTH),540)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_28x28
endif
ifeq ($(LCM_WIDTH),480)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_28x28
endif
ifeq ($(LCM_WIDTH),320)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_20x20
endif
ifeq ($(LCM_WIDTH),240)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_20x20
endif

else
ifeq ($(LCM_WIDTH),1440)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1400)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1280)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1152)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1080)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1050)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),1024)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),960)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),864)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),800)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),720)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_24x44
endif
ifeq ($(LCM_WIDTH),600)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_16x30
endif
ifeq ($(LCM_WIDTH),540)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_16x28
endif
ifeq ($(LCM_WIDTH),480)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_16x28
endif
ifeq ($(LCM_WIDTH),320)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_10x18
endif
ifeq ($(LCM_WIDTH),240)
	LOCAL_CFLAGS += -DFEATURE_FTM_FONT_10x18
endif
endif
