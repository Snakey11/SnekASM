
.macro SET_FUNC name, value
    .global \name
    .type   \name, %function
    .set    \name, \value
.endm

SET_FUNC AntiWeaponTriangle, (0x0802C834+1)
