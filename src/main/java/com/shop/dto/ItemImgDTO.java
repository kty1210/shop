package com.shop.dto;

import com.shop.config.common.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDTO {

    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn; //대표 이미지

    public static ModelMapper modelMapper = new ModelMapper();

    /* 간략화 */
    public static ItemImgDTO of (ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }

    /*
    public static ItemImgDTO entityToDTO(ItemImg itemImg){
        ItemImgDTO itemImgDTO = ItemImgDTO.builder()
                .id(itemImg.getId())
                .imgName(itemImg.getOriImgName())
                .oriImgName(itemImg.getImgName())
                .imgUrl(itemImg.getImgUri())
                .repImgYn(itemImg.getRepimgYn())
                .build();

        return itemImgDTO;
    }
 */
}
